package com.cqrs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by water on 2016/4/15.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreatedEvent implements Serializable {

    private static final long serialVersionUID = 4481319121394019789L;

    private String id;

    private String name;

}
