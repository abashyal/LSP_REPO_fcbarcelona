# Question 1 Answers – Concurrency in RequestManager

## Part 1: Shared Resources and Risk

**Shared Resource #1:** `nextId` (the integer counter used to generate unique request IDs)

**Shared Resource #2:** `requests` (the `ArrayList<String>` that stores all submitted requests)

**Concurrency Problem:**
A race condition can occur when multiple threads call `addRequest()` simultaneously. Two threads may read the same value of `nextId` before either increments it, causing duplicate IDs to be assigned to different requests. Additionally, `ArrayList` is not thread-safe, so concurrent `add()` calls can corrupt the list (e.g., lost updates, index-out-of-bounds errors).

**Why `addRequest()` is unsafe:**
`addRequest()` is a compound operation: it calls `getNextId()` (read-then-increment of `nextId`) and then calls `requests.add()`. These two steps are not atomic. Between the `getNextId()` call and the `requests.add()` call, another thread can interleave and perform the same sequence, resulting in duplicate IDs and non-atomic writes to the shared list. Neither `nextId` nor `ArrayList` provides any built-in synchronization.

---

## Part 2: Evaluate Fixes

**Fix A: `public synchronized int getNextId() { ... }`**
**Incorrect.** Synchronizing only `getNextId()` ensures that the counter increment is atomic, so no two threads will receive the same ID. However, it does not protect `requests.add()` in `addRequest()`. Two threads can still interleave between the call to `getNextId()` and the call to `requests.add()`, and concurrent `add()` calls to the unsynchronized `ArrayList` can still corrupt the list. The fix is incomplete.

**Fix B: `public synchronized void addRequest(String studentName) { ... }`**
**Correct.** Synchronizing `addRequest()` as a whole makes the entire compound operation—getting the next ID and adding to the list—atomic. Only one thread can execute `addRequest()` at a time. Since `getNextId()` is called exclusively from within the now-synchronized `addRequest()`, no two threads can interleave. Both shared resources (`nextId` and `requests`) are fully protected. This correctly solves the concurrency problem.

**Fix C: `public synchronized List<String> getRequests() { ... }`**
**Incorrect.** Synchronizing the read-only getter does nothing to protect the write path. The race condition in `addRequest()`—the non-atomic read-increment-add sequence—is entirely unaffected by synchronizing `getRequests()`. Duplicate IDs and list corruption can still occur. The fix does not address the problem at all.

---

## Part 3: Object-Oriented Design

**Answer:** No, `getNextId()` should **not** be public.

**Explanation:**
According to Arthur Riel's heuristics, a class should minimize its public interface and expose only what external clients genuinely need (Heuristic 2.1: "All data should be hidden within its class"). `getNextId()` is an internal implementation detail that supports `addRequest()`; no external caller needs to generate or consume a raw ID directly. Making it public violates encapsulation by exposing internal state management and invites misuse—callers could call `getNextId()` out of sequence and silently corrupt the ID sequence. The method should be `private` so that ID generation is fully controlled within the class.

---

## Part 4: Alternative Synchronization Approach

**Description:**
The alternative approach discussed in lecture is using `java.util.concurrent` classes—specifically `AtomicInteger` for lock-free atomic counter updates and `CopyOnWriteArrayList` (or a `Collections.synchronizedList`) for thread-safe list operations. `AtomicInteger` provides a `getAndIncrement()` method that reads and increments the counter as a single, indivisible CPU-level operation, eliminating the race condition on `nextId` without using `synchronized`. Pairing it with a thread-safe list variant covers both shared resources without any explicit locking.

**Code Snippet:**
```java
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestManager {
    private final AtomicInteger nextId = new AtomicInteger(1);
    private final List<String> requests = new CopyOnWriteArrayList<>();

    public void addRequest(String studentName) {
        int id = nextId.getAndIncrement();
        requests.add("Request-" + id + " from " + studentName);
    }

    public List<String> getRequests() {
        return requests;
    }
}
```
