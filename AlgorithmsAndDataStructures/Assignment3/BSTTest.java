import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 17/12/20
 *
 *  @author  Sean Gaffney
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  
  @Test
  public void testIsEmpty() {
    BST<Integer, Integer> test = new BST<Integer, Integer>();
    assertTrue("Test if empty",test.isEmpty());  
  }

  @Test
  public void testSize() {
    BST<Integer, Integer> test = new BST<Integer, Integer>();
    assertEquals("is empty", test.size(), 0);
    test.put(2,2);
    assertEquals("single element", test.size(), 1);
    test.put(1,1);
    test.put(3,3);
    assertEquals("3 elements", test.size(), 3);
  }

  @Test
  public void testContains() {
    BST<Integer, Integer> test = new BST<Integer, Integer>();
    assertFalse("is empty",  test.contains(0));
    test.put(1,1);
    assertTrue("contains value", test.contains(1));
    assertFalse("no value", test.contains(5));
  }

  @Test
  public void testGet() {
    BST<Integer, Integer> test = new BST<Integer, Integer>();
    assertNull("empty", test.get(1));
    test.put(1, 1);
    assertNull("no key", test.get(0));
    assertEquals("Key exists", (int) test.get(1), 1);
    test.put(2, 2);
    assertEquals("another key", (int) test.get(2), 2);
  }

  @Test
  public void testHeight() {
    BST<Integer, Integer> test = new BST<Integer, Integer>();
    assertEquals("empty", test.height(), -1);
    test.put(3, 3);
    assertEquals("1 element", test.height(), 0);
    test.put(1, 1);
    assertEquals("2 elements", test.height(), 1);
    test.put(0, 0);
    assertEquals("3 elements", test.height(), 2);
  }

  @Test
  public void testMedian() {
    BST<Integer, Integer> test = new BST<Integer, Integer>();
    assertNull(test.median());
    test.put(6, 6);
    assertTrue("checks median",test.median().equals(6));
    test.put(8, 8);
    test.put(5, 5);
    test.put(16, 16);
    test.put(12, 12);
    assertTrue("check median",test.median().equals(8));
    test = new BST<Integer, Integer>();
    test.put(15, 15);
    test.put(25, 25);
    test.put(16, 16);
    test.put(9, 9);
    test.put(8, 8);
    test.put(12, 12);
    assertTrue("check median",test.median().equals(12));
  }

  @Test
  public void testPut() {
      BST<Integer, Integer> test = new BST<Integer, Integer>();
      test.put(1, 1);
      test.put(1, null);
      test.put(2, 3);
      test.put(4, 3);
      test.put(2, 2);
  }
  
  @Test
  public void testPrintKeysInOrder() {
      BST<Integer, Integer> test = new BST<Integer, Integer>();
      assertEquals("()", test.printKeysInOrder());
      test.put(10, 10);
      assertEquals("(()10())", test.printKeysInOrder());
      test.put(1, 1);
      test.put(0, 0);
      test.put(5, 5);
      assertEquals("(((()0())1(()5()))10())", test.printKeysInOrder());
  }

  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

  
     /** <p>Test {@link BST#delete(Comparable)}.</p> */
     @Test
     public void testDelete() {
         BST<Integer, Integer> bst = new BST<Integer, Integer>();
         bst.delete(1);
         assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
         
         bst.put(7, 7);   //        _7_
         bst.put(8, 8);   //      /     \
         bst.put(3, 3);   //    _3_      8
         bst.put(1, 1);   //  /     \
         bst.put(2, 2);   // 1       6
         bst.put(6, 6);   //  \     /
         bst.put(4, 4);   //   2   4
         bst.put(5, 5);   //        \
                          //         5
         
         assertEquals("Checking order of constructed tree",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
         
         bst.delete(9);
         assertEquals("Deleting non-existent key",
                 "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
 
         bst.delete(8);
         assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());
 
         bst.delete(6);
         assertEquals("Deleting node with single child",
                 "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());
 
         bst.delete(3);
         assertEquals("Deleting node with two children",
                 "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
     }
     
}
