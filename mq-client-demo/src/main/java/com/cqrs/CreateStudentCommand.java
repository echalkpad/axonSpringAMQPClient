package com.cqrs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by water on 2016/4/15.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateStudentCommand {

    private String id;

    private String name;

}
