package com.ssafy.sopy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Test {

    @Id @GeneratedValue
    @Column(name = "test_id")
    private Long id;

    public String content;

}
