For starvation freedom, I implemented a FIFO queue which satisfies First Come First Serve. Since First come First Serve is starvation-free, so does my idea.

For mutual exclusion, thread will release Alice's pets in situations below:
Before explaination, I introduce a hashmap to count the number of pets that are released in the yard.
Then:
A thread can enter critical section when:
1) if the value of hashmap of the thread is not 0, so the thread has released some pets before, then the thread can enter the yard.
   More detailed, like a basic test, a thread repeatedly calls yard.enterAlicePet() twice. If it enters after the first call, then so will the second call. (mutual exclusion since Alice's pet in the yard, there must have no Bob's pet)
2)else (the value is 0), but the thread is the first of queue now and there is no Bob's pet, then the thread can enter the yard.

When leaving the yard, the hashmap will count the number in the yard Alice's or Bob's. And the main idea is shown above. In this way, it will resolve deadlock.

And because of this, it satisfies mutual exclusion and starvation freedom no matter the order of these threads are.