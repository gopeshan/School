#include "engine.h"
#include "util.h"
#include <ctype.h>

bool run_statements(struct context *ctx, const char **input, struct error *err) {
    skip_whitespace(input);

    if (**input == '\0' || **input == '}')
        return true;

    return run_statement(ctx, input, err) && run_statements(ctx, input, err);
}

bool run_statement(struct context *ctx, const char **input, struct error *err) {
    // Skip leading whitespace
    skip_whitespace(input);

    // Check if input starts with ENQ
    if (starts_with(*input, "ENQ")) {
        // Move past "ENQ"
        (*input) += 3;
        skip_whitespace(input);

        // Check for an opening parenthesis "("
        if (!starts_with(*input, "(")) {
            err->pos = *input;
            err->desc = "Expected '(' after ENQ";
            return false;
        }

        // Move past "("
        (*input)++;

        // Evaluate the expression within ENQ and store the result in 'value'
        int value;
        if (!eval_expression(ctx, input, err, &value)) return false;

        // Enqueue the value
        enqueue(&ctx->q, value);

        // Check for a closing parenthesis ")"
        skip_whitespace(input);
        if (!starts_with(*input, ")")) {
            err->pos = *input;
            err->desc = "Expected ')' after expression in ENQ";
            return false;
        }

        // Move past ")"
        (*input)++;

        // Check for a semicolon at the end of the statement
        skip_whitespace(input);
        if (**input != ';') {
            err->pos = *input;
            err->desc = "Expected ';' at the end of ENQ statement";
            return false;
        }

        // Move past ";"
        (*input)++;
        return true;
    }

        //Check if input starts with ASSERT
    else if (starts_with(*input, "ASSERT")) {
        // Move past "ASSERT"
        (*input) += 6;
        skip_whitespace(input);

        // Check for an opening parenthesis "("
        if (**input != '(') {
            err->pos = *input;
            err->desc = "Expected '(' after ASSERT";
            return false;
        }

        // Move past "("
        (*input)++;

        // Evaluate the condition within ASSERT
        bool condition;
        if (!eval_condition(ctx, input, err, &condition)) return false;

        // Check if the condition is false, indicating assertion failure
        if (!condition) {
            err->pos = *input;
            err->desc = "Assertion failed";
            return false;
        }

        // Check for a closing parenthesis ")"
        skip_whitespace(input);
        if (**input != ')') {
            err->pos = *input;
            err->desc = "Expected ')' after ASSERT condition";
            return false;
        }

        // Move past ")"
        (*input)++;

        // Check for a semicolon at the end of the statement
        skip_whitespace(input);
        if (**input != ';') {
            err->pos = *input;
            err->desc = "Expected ';' at the end of ASSERT statement";
            return false;
        }
        // Move past ";".
        (*input)++;
        return true;

    }

        //Check if input starts with x or y
    else if (**input == 'x' || **input == 'y') {
        // Identify the variable ('x' or 'y')
        char var = **input;

        // Move past 'x' or 'y'
        (*input)++;
        skip_whitespace(input);

        // Check for an equal sign for assignment
        if (**input != '=') {
            err->pos = *input;
            err->desc = "Expected '=' in assignment";
            return false;
        }

        // Move past "="
        (*input)++;

        // Evaluate the expression on the right-hand side and store it in 'tempValue'
        int tempValue;
        if (!eval_expression(ctx, input, err, &tempValue)) return false;

        // Assign the result to the appropriate variable (x or y)
        if (var == 'x') {
            ctx->x = tempValue;
        } else {
            ctx->y = tempValue;
        }

        // Check for a semicolon at the end of the statement
        skip_whitespace(input);
        if (**input != ';') {
            err->pos = *input;
            err->desc = "Expected ';' at the end of assignment";
            return false;
        }
        // Move past ";".
        (*input)++;
        return true;

    }

        //Check if input starts with WHILE
    else if (starts_with(*input, "WHILE")) {
        // Move past "WHILE".
        (*input) += 6;
        skip_whitespace(input);

        // Check for an opening parenthesis "("
        if (**input != '(') {
            err->pos = *input;
            err->desc = "Expected '(' after WHILE";
            return false;
        }

        // Move past "("
        (*input)++;

        // Setting start and end positions for the conditional WHILE
        const char *condition_start = *input;
        bool condition = false;
        const char *condition_end = condition_start;

        // Evaluate the condition within WHILE
        if (!eval_condition(ctx, &condition_end, err, &condition)) {
            return false;
        }
        *input = condition_end;

        // Check for a closing parenthesis ")"
        skip_whitespace(input);
        if (**input != ')') {
            err->pos = *input;
            err->desc = "Expected ')' after WHILE condition";
            return false;
        }

        // Move past ")"
        (*input)++;

        // Check for the opening brace '{' to start the loop body
        skip_whitespace(input);
        if (**input != '{') {
            err->pos = *input;
            err->desc = "Expected '{' to start the loop body";
            return false;
        }

        // Move past "{"
        (*input)++;

        // Set the starting character of loop
        const char *loop_start = *input;
        int brace_count = 1;

        // Find the matching closing brace for the loop
        while (*input && brace_count > 0) {
            if (**input == '{') brace_count++;
            else if (**input == '}') brace_count--;
            (*input)++;
        }

        //Set the loop end character
        const char *loop_end = *input - 1;

        // Execute the loop body while the condition is true
        do {
            const char *loop_body = loop_start;
            if (!run_statements(ctx, &loop_body, err) || loop_body != loop_end) {
                return false;
            }

            condition_end = condition_start;

            // Reevaluate the condition to check if the loop should continue
            if (!eval_condition(ctx, &condition_end, err, &condition)) {
                return false;
            }
        } while (condition);
        return true;
    }

        //None of the characters match, return false
    else {
        err->pos = *input;
        err->desc = "Unexpected token";
        return false;
    }
}

bool eval_expression(struct context *ctx, const char **input, struct error *err, int *out) {
    skip_whitespace(input);

    // Initialize a variable to store the left-hand side of the expression
    int lhs;

    // Check if the input starts with "x"
    if (starts_with(*input, "x")) {
        // Move past x
        (*input)++;

        // Set lhs to the value of x in the context.
        lhs = ctx->x;
    }

        // Check if the input starts with "y"
    else if (starts_with(*input, "y")) {
        // Move past y
        (*input)++;

        // Set lhs to the value of y in the context
        lhs = ctx->y;
    }

        // Check if the input starts with "DEQ"
    else if (starts_with(*input, "DEQ")) {

        // Move past "DEQ".
        (*input) += 3;

        // Try to dequeue from the queue.
        if (!dequeue(&ctx->q, &lhs)) {
            err->pos = *input;
            err->desc = "Attempted to dequeue from an empty queue";
            return false;
        }

    }

        // Check if the input is a digit, plus or minus sign
    else if (isdigit(**input) || **input == '-' || **input == '+') {
        // Try to parse an integer.
        if (!parse_integer(input, err, &lhs)) {
            return false;
        }
    }

        //If input is none of the above, error
    else {
        err->pos = *input;
        err->desc = "Unexpected token in expression";
        return false;
    }

    skip_whitespace(input);

    // Check for addition operation
    while (**input == '+') {
        // Move past '+'
        (*input)++;
        skip_whitespace(input);

        // Initialize a variable for the right-hand side.
        int rhs;

        // Check if the input starts with x
        if (starts_with(*input, "x")) {
            // Move past x
            (*input)++;
            // Set rhs to the value of x in the context.
            rhs = ctx->x;
        }

            // Check if the input starts with y
        else if (starts_with(*input, "y")) {
            // Move past y
            (*input)++;
            // Set rhs to the value of y in the context
            rhs = ctx->y;
        }

            // Check if the input starts with "DEQ"
        else if (starts_with(*input, "DEQ")) {
            // Move past "DEQ"
            (*input) += 3;

            // Try to dequeue from the queue.
            if (!dequeue(&ctx->q, &rhs)) {
                err->pos = *input;
                err->desc = "Attempted to dequeue from an empty queue";
                return false;
            }
        }

            // Check if the input is a digit or +/-
        else if (isdigit(**input) || **input == '-' || **input == '+') {
            // Try to parse an integer
            if (!parse_integer(input, err, &rhs)) {
                return false;
            }
        }

            // Check for a nested expression in parentheses
        else if (**input == '(') {
            // Move past '('
            (*input)++;

            // Evaluate the nested expression.
            if (!eval_expression(ctx, input, err, &rhs)) {
                return false;
            }

            //Check for a closing parenthesis
            if (**input != ')') {
                err->pos = *input;
                err->desc = "Expected ')' after nested expression";
                return false;
            }

            // Move past ')'
            (*input)++;

        }

            // If none of the above, return false
        else {
            err->pos = *input;
            err->desc = "Unexpected token in addition";
            return false;
        }

        // Perform addition
        lhs += rhs;
        skip_whitespace(input);
    }

    // Return the result of the expression
    *out = lhs;
    return true;
}

bool eval_condition(struct context *ctx, const char **input, struct error *err, bool *out) {
    skip_whitespace(input);

    // Check for "EMPTY" condition
    if (starts_with(*input, "EMPTY")) {
        // Move past "EMPTY".
        (*input) += 5;

        // Set the result to whether the queue is empty
        *out = queue_empty(&ctx->q);
        return true;
    }
/*
        // Check for "ASSERT" condition
    else if (starts_with(*input, "ASSERT")) {
        // Move past "ASSERT"
        (*input) += 6;
        skip_whitespace(input);

        // Check for an opening parenthesis
        if (**input != '(') {
            err->pos = *input;
            err->desc = "Expected '(' after ASSERT";
            return false;
        }
        (*input)++;
        bool condition;

        // Evaluate the nested condition
        if (!eval_condition(ctx, input, err, &condition)) {
            return false;
        }

        //If not, assertion failed
        if (!condition) {
            err->pos = *input;
            err->desc = "Assertion failed";
            return false;
        }

        skip_whitespace(input);

        // Check for a closing parenthesis
        if (**input != ')') {
            err->pos = *input;
            err->desc = "Expected ')' after ASSERT condition";
            return false;
        }

        (*input)++;
        skip_whitespace(input);

        // Check for a semicolon at the end of the statement
        if (**input != ';') {
            err->pos = *input;
            err->desc = "Expected ';' at the end of ASSERT statement";
            return false;
        }
        (*input)++;

        // Set the result to true for a successful assertion.
        *out = true;
        return true;
    }
    */
        //Comparison condition
    else {
        int lhs, rhs;

        // Evaluate the left-hand side.
        if (!eval_expression(ctx, input, err, &lhs)) {
            err->pos = *input;
            err->desc = "Unexpected operator in condition";
            return false;
        }

        skip_whitespace(input);
        char op = **input;
        (*input)++;

        // Checking for equal sign operator
        if (op == '=') {
            // Evaluate the right-hand side
            if (!eval_expression(ctx, input, err, &rhs)) return false;
            // Set the result to the comparison result
            *out = (lhs == rhs);
        }
            // Checking for less than operator
        else if (op == '<') {
            // Evaluate the right-hand side
            if (!eval_expression(ctx, input, err, &rhs)) return false;
            // Set the result to the comparison result
            *out = (lhs < rhs);
        }

            //Non supported operator
        else {
            err->pos = *input;
            err->desc = "Unexpected operator in condition";
            return false;
        }
        return true;
    }
}

bool parse_integer(const char **input, struct error *err, int *out) {
    const char *before = *input;
    int result = (int) strtol(before, (char **) input, 10);
    if (!isspace(**input) && **input != ';' && **input != ')' && **input != '+' && !isdigit(**input)) {
        err->pos = *input;
        err->desc = "Unexpected character after number";
        return false;
    }

    if (*input == before) {
        err->pos = before;
        err->desc = "expected an integer";
        return false;
    } else {
        *out = result;
        return true;
    }
}