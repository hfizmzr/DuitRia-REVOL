/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DuitRia;

/**
 *
 * @author Ezra
 */
public class TaxCollection {
    public void CollectTax(Players player){
        System.out.println("You have to pay tax");
        player.Balance -= 2000000;
    }
}
