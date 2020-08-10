/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import domain.Guest;
import java.util.List;

/**
 *
 * @author Katica
 */
public interface ServiceGuest {
    public Guest saveGuest(Guest guest) throws Exception;

    public List<Guest> getAllGuest() throws Exception;
}
