/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domain.User;

/**
 *
 * @author Katica
 */
public interface ServiceUser {
    User login(String username,String password) throws Exception;
}
