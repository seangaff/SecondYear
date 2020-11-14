#include "infix.h"
/*
for each token in the input string {
  if ( token is a number ) {
    append token to the output string
  }
  else if (token is a left bracket) {
    push bracket to stack
  }
  else if ( token in an operator ) {
    while ( there is operator on top of stack with equal or higher precedence )
{ pop stack and append popped operator to output string
    }
    push token operator to stack
  }
  else if ( token is right bracket ) {
    while ( top of stack != '(' ) {
      pop operator from stack and append to output string
    pop left bracket
  }
}
pop remaining stack items and append each of them to the end of your reverse
Polish notation expression.
*/
int operatorType(char *);

// evaluate expression stored as an array of string tokens
double evaluate_infix_expression(char **args, int nargs) {
    struct double_stack *stack = double_stack_new(50);
    char **postFormat = malloc(sizeof(char *) * nargs);
    int postIndex = 0;

    for (int i = 0; i < nargs; i++) {
        int operator= 0;
        if (strcmp(args[i], "+") == 0) {
            operator= 1;
        } else if (strcmp(args[i], "-") == 0) {
            operator= 2;
        } else if (strcmp(args[i], "X") == 0) {
            operator= 3;
        } else if (strcmp(args[i], "/") == 0) {
            operator= 4;
        } else if (strcmp(args[i], "^") == 0) {
            operator= 5;
        } else if (strcmp(args[i], "(") == 0) {
            double_stack_push(stack, i);
        } else if (strcmp(args[i], ")") == 0) {
            while (strcmp(args[(int)(stack->items[stack->top - 1])], "(") !=
                   0) {
                double expIndex = double_stack_pop(stack);
                postFormat[postIndex] = args[(int)expIndex];
                postIndex++;
            }
            double_stack_pop(stack);
        } else {  // Numbers
            printf("%s ", args[i]);
            postFormat[postIndex] = args[i];
            postIndex++;
        }

        if (operator > 0) {
            if (stack->top != 0) {
                char *firstOut = args[(int)(stack->items[stack->top - 1])];
                int newOp = operator;
                int oldOp = operatorType(firstOut);
                while (oldOp >= newOp && stack->top != 0) {
                    postFormat[postIndex] = firstOut;
                    printf("Added %s\n", firstOut);
                    double_stack_pop(stack);
                    postIndex++;
                    firstOut = args[(int)(stack->items[stack->top - 1])];
                    oldOp = operatorType(firstOut);
                }
            }
            double_stack_push(stack, i);
        }
    }
    while (stack->top != 0) {
        double expIndex = double_stack_pop(stack);
        postFormat[postIndex] = args[(int)expIndex];
        postIndex++;
    }
    return evaluate_postfix_expression(postFormat, postIndex);
}

int operatorType(char *symbol) {
    if (strcmp(symbol, "+") == 0) {
        return 1;
    } else if (strcmp(symbol, "-") == 0) {
        return 2;
    } else if (strcmp(symbol, "X") == 0) {
        return 3;
    } else if (strcmp(symbol, "/") == 0) {
        return 4;
    } else if (strcmp(symbol, "^") == 0) {
        return 5;
    } else
        return 0;
}