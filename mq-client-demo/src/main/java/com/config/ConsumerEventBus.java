package com.config;

import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.String.format;

/**
 * Created by water on 2016/4/15.
 */


public class ConsumerEventBus extends ClusteringEventBus{

    private EventBusTerminal remoteTerminal;

    private EventBusTerminal localTerminal;

    private final ClusterSelector clusterSelector;

    private final Set<Cluster> clusters = new HashSet<Cluster>();

    private final Cluster localCluster;


    public ConsumerEventBus(ClusterSelector clusterSelector,EventBusTerminal remoteTerminal,EventBusTerminal localTerminal,Cluster localCluster){
        this.remoteTerminal = remoteTerminal;
        this.localTerminal = localTerminal;
        this.clusterSelector = clusterSelector;
        this.localCluster = localCluster;
    }


    public ConsumerEventBus(ClusterSelector clusterSelector,EventBusTerminal remoteTerminal,Cluster localCluster){
        this(clusterSelector,remoteTerminal,new SimpleEventBusTerminal(),localCluster);
    }

    public ConsumerEventBus(ClusterSelector clusterSelector,EventBusTerminal remoteTerminal){
        this(clusterSelector,remoteTerminal,new SimpleEventBusTerminal(),null);
    }

    @Override
    public void publish(EventMessage... events) {
            localTerminal.publish(events);
    }

    @Override
    public void subscribe(EventListener eventListener) {
        clusterFor(eventListener).subscribe(eventListener);
    }


    private synchronized Cluster clusterFor(EventListener eventListener) {
        Cluster cluster = clusterSelector.selectCluster(eventListener);
        if (cluster == null) {
            Class listenerType = eventListener.getClass();
            if (eventListener instanceof EventListenerProxy) {
                listenerType = ((EventListenerProxy) eventListener).getTargetType();
            }
            throw new EventListenerSubscriptionFailedException(format(
                    "Unable to subscribe [%s] to the Event Bus. There is no suitable cluster for it. "
                            + "Make sure the ClusterSelector is configured properly",
                    listenerType.getName()));
        }
        if (clusters.add(cluster)) {
                if(!localCluster.equals(cluster)){
                    remoteTerminal.onClusterCreated(cluster);
                }
                localTerminal.onClusterCreated(cluster);

        }
        return cluster;
    }


    private static class SimpleEventBusTerminal implements EventBusTerminal {

        private final List<Cluster> clusters = new CopyOnWriteArrayList<Cluster>();

        @Override
        public void publish(EventMessage... events) {
            for (Cluster cluster : clusters) {
                cluster.publish(events);
            }
        }

        @Override
        public void onClusterCreated(Cluster cluster) {
            clusters.add(cluster);
        }
    }

}
