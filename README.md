# st-lse-coding-interview-template
## Instructions
Task: Implement the transfer method in the BankService class.
The method must be thread-safe; multiple threads will call this method concurrently.
You must prevent deadlocks (e.g., if User A transfers to User B while User B transfers to User A simultaneously).
Ensure the from account has sufficient funds.
Use the provided Account class (assume it has getBalance(), deposit(), and withdraw() methods).
