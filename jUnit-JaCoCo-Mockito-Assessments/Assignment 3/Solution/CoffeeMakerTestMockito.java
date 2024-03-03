/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 *
 * Permission has been explicitly granted to the University of Minnesota
 * Software Engineering Center to use and distribute this source for
 * educational purposes, including delivering online education through
 * Coursera or other entities.
 *
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including
 * fitness for purpose.
 *
 * Modifications
 * 20171113 - Michael W. Whalen - Extended with additional recipe.
 * 20171114 - Ian J. De Silva   - Updated to JUnit 4; fixed variable names.
 */
package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.mockito.ArgumentCaptor;


/**
 * Unit tests for CoffeeMaker class.
 *
 * @author Akshay Singh
 *
 * Extended by Mike Whalen
 */

public class CoffeeMakerTest {

	//-----------------------------------------------------------------------
	//	DATA MEMBERS
	//-----------------------------------------------------------------------
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;

	private Recipe [] stubRecipies;

	/**
	 * The coffee maker -- our object under test.
	 */
	private CoffeeMaker coffeeMaker;

	// Inventory object for our mocks assignment:
	private Inventory inv;

	/**
	 * The stubbed recipe book.
	 */
	private RecipeBook recipeBookStub;


	//-----------------------------------------------------------------------
	//	Set-up / Tear-down
	//-----------------------------------------------------------------------
	/**
	 * Initializes some recipes to test with, creates the {@link CoffeeMaker}
	 * object we wish to test, and stubs the {@link RecipeBook}.
	 *
	 * @throws RecipeException  if there was an error parsing the ingredient
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {

		recipeBookStub = mock(RecipeBook.class);
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());

		//Set up for recipe1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("3");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");

		//Set up for recipe2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");

		//Set up for recipe3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");

		//Set up for recipe4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");

		//Set up for recipe5 (added by MWW)
		recipe5 = new Recipe();
		recipe5.setName("Super Hot Chocolate");
		recipe5.setAmtChocolate("6");
		recipe5.setAmtCoffee("20");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("100");

		stubRecipies = new Recipe [] {recipe1, recipe2, recipe3};
	}


	//-----------------------------------------------------------------------
	//	Test Methods
	//-----------------------------------------------------------------------

	// put your tests here!

    	@Test
	public void testMakeCoffee() 
	{
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		assertTrue(true);
	}
    @Test
    public void testMakeCoffee1() throws RecipeException 
	{
        RecipeBook recipeInstance = mock(RecipeBook.class);
        Inventory inventInstance = new Inventory(); //mock(Inventory.class);
        when(recipeInstance.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker = new CoffeeMaker(recipeInstance, inventInstance);
        assertEquals(15, coffeeMaker.makeCoffee(0, 65));
        assertEquals(12, inventInstance.getCoffee());
    }
	@Test
    public void testMakeCoffee2()
	{
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker.addRecipe(stubRecipies[0]);
        coffeeMaker.addRecipe(stubRecipies[1]);
        coffeeMaker.addRecipe(stubRecipies[2]);
        assertEquals(175, coffeeMaker.makeCoffee(4, 175));
    }
	@Test
    public void testMakeCoffee3() 
	{
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker.addRecipe(stubRecipies[0]);
        assertEquals(75, coffeeMaker.makeCoffee(1, 75));
    }
	@Test
    public void testMakeCoffee4() 
	{
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker.addRecipe(stubRecipies[0]);
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }
    @Test
    public void testAddRecipe() 
	{
        when(recipeBookStub.addRecipe(stubRecipies[0])).thenReturn(true);
        when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(true);
        when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(true);
        when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(false);
    }
    @Test
    public void testAddRecipe1() 
	{
        when(recipeBookStub.addRecipe(stubRecipies[0])).thenReturn(true);
        when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(true);
        when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(false);
        when(recipeBookStub.addRecipe(recipe4)).thenReturn(false);
        when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(true);
    }
    @Test
    public void testDeleteRecipe()
    {   
		coffeeMaker.addRecipe(stubRecipies[0]);
        coffeeMaker.addRecipe(stubRecipies[1]);
        coffeeMaker.addRecipe(stubRecipies[2]);
        when(recipeBookStub.deleteRecipe(0)).thenReturn("Coffee");
        when(recipeBookStub.deleteRecipe(1)).thenReturn("Mocha");
        when(recipeBookStub.deleteRecipe(2)).thenReturn("Latte");
        when(recipeBookStub.deleteRecipe(3)).thenReturn(null);
    }
	
	@Test
    public void testAmountDummy() throws RecipeException 
	{
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker.addRecipe(stubRecipies[2]);
        assertEquals(stubRecipies[2].getName(), "Latte");
        assertEquals(stubRecipies[2].getAmtChocolate(), 0);
    }

    @Test
    public void testAmountDummy1() throws Exception 
	{
        when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
        coffeeMaker.addRecipe(stubRecipies[2]);
        assertEquals(stubRecipies[2].getName(), "Latte");
        assertEquals(stubRecipies[2].getAmtChocolate(), 0);
        assertEquals(stubRecipies[2].getAmtCoffee(), 3);
        assertEquals(stubRecipies[2].getAmtMilk(), 3);
        assertEquals(stubRecipies[2].getAmtSugar(), 1);
        assertEquals(stubRecipies[2].getPrice(), 100);
    }
}




