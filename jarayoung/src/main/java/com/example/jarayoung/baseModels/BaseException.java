package com.example.jarayoung.baseModels;
/**
 * Basic Exception Model class
 *
 * @author LEE JIHO
 * @since 22.12.02
 * */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends  Exception{
    private BasicServerStatus status;
}
