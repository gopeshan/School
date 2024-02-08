#include "demo.h"
#include "stack.h"

bool check_brackets(const char *str) {
    stack_ptr s = stack_new();

    // TODO (task 3): using the stack 's', check the brackets in the strings.
    // Iterate through the characters in the input string
    char out;
    int count=0;

    while(str[count] != '\n'){
        // Check each character in the input string
        switch (str[count]){
            case '(':
                // Push the character onto the stack
                stack_push(s,str[count]);
                break;
            case '{':
                stack_push(s,str[count]);
                break;
            case '[':
                stack_push(s,str[count]);
                break;

            case ')':
                // Push the character onto the stack
                stack_pop(s,&out);
                // Check if the popped character doesn't match the expected opening bracket
                if(out!='(')
                    return false;
                break;
            case '}':
                stack_pop(s,&out);
                if(out!='{')
                    return false;
                break;
            case ']':
                stack_pop(s,&out);
                if(out!='[')
                    return false;
                break;
        }
        out = '\n';   // Reset 'out' to a newline character
        count++;      // Move to the next character in the input string
    }
    if (stack_pop(s, &out))
        return false; // If there's anything left in the stack, return 'false' (brackets are not balanced)

    stack_free(s); // Free the memory of the stack
    return true;   // If all brackets are balanced, return 'true'
}