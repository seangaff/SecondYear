#include "postfix.h"

/*
for each token in the input string {
  if ( token is a number ) {
    push token onto the stack
  }
  else if ( token is an operator ) {
    pop the top two items off the stack
    apply the operator to the two items
    push the result onto the stack
  }
}
*/

// operators: +, -, X, /, ^

// evaluate expression stored as an array of string tokens
double evaluate_postfix_expression(char **args, int nargs) {
    struct double_stack *numTop = double_stack_new(100);

    for (int i = 0; i < nargs; i++) {
        printf("%s", args[i]);
        if (strcmp(args[i], "+") == 0) {
            double num1 = double_stack_pop(numTop);
            double num2 = double_stack_pop(numTop);
            double_stack_push(numTop, num1 + num2);
        } else if (strcmp(args[i], "-") == 0) {
            double num1 = double_stack_pop(numTop);
            double num2 = double_stack_pop(numTop);
            double_stack_push(numTop, num2 - num1);
        } else if (strcmp(args[i], "X") == 0) {
            double num1 = double_stack_pop(numTop);
            double num2 = double_stack_pop(numTop);
            double_stack_push(numTop, num1 * num2);
        } else if (strcmp(args[i], "/") == 0) {
            double num1 = double_stack_pop(numTop);
            double num2 = double_stack_pop(numTop);
            double_stack_push(numTop, num2 / num1);
        } else if (strcmp(args[i], "^") == 0) {
            double num1 = double_stack_pop(numTop);
            double num2 = double_stack_pop(numTop);
            double_stack_push(numTop, pow(num2, num1));
        } else {
            double num = atof(args[i]);
            double_stack_push(numTop, num);
        }
    }
    return double_stack_pop(numTop);
}
