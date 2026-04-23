# Calculator & Lamp App (Android)

This project contains two tasks:
1. **Task 1: Lamp and Bulb** - Implementation of classes with specific state rules and a built-in test mechanism.
2. **Task 2 & 3: Calculator** - A functional calculator with sequential evaluation, expression support (RPN/Shunting-yard), and number base conversion.

## Features
- **Lamp & Bulb**: 
    - Private fields, intensity range 0-10.
    - Automatic bulb burnout if intensity exceeds 10.
    - UI-based test report showing all required scenarios.
- **Calculator**:
    - **Basic**: Digits, Clear, Equals, and Power (num) button.
    - **Advanced**: Shunting-yard algorithm for operator precedence (e.g., 2 + 2 * 2 = 6).
    - **Extended**: Base conversion (Binary, Octal, Hex) and History screen.
    - **Responsive**: Layout scales properly on different devices.

## Build Information
The debug APK is available in the `release/` folder of this repository.

### APK Download
[Download app-debug.apk](./release/app-debug.apk)

## Screenshots
Screenshots of the Lamp tests and Calculator UI are provided in the repository.

## Lab Answer
**Hardest technical issue:** Implementing the Shunting-yard algorithm to handle operator precedence while maintaining a responsive UI state. 
**Solution:** I used a Stack-based approach to convert the infix expression string into Postfix (RPN), ensuring that multiplication and power operations are evaluated before addition/subtraction.
