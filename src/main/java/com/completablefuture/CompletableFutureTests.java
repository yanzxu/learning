package com.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTests {
       public static void main(String[] args) throws Exception {
          /* // ======== 手动完成future ==========
           CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
           completableFuture1.complete("Complete manually！");
           System.out.println("=== " + Thread.currentThread().getId() + " === " + completableFuture1.get());

           // ======== 运行异步计算: 无返回结果 ==========
           CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
               try {
                   TimeUnit.SECONDS.sleep(3);
                   System.out.println("=== " + Thread.currentThread().getId() + " === " +  "sleep 3 seconds");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           });
           completableFuture2.get();

           // ======== 运行异步计算: 有返回结果 ==========
           CompletableFuture completableFuture3 = CompletableFuture.supplyAsync(() -> {
               System.out.println("=== " + Thread.currentThread().getId() + " === " + "运行异步计算，有返回值");
               return "supplyAsync";
           });
           System.out.println("=== " + Thread.currentThread().getId() + " === " + completableFuture3.get());

           // ======== 运行异步计算, 并提供线程池 ==========
           CompletableFuture completableFuture4 = CompletableFuture
                   .supplyAsync(() -> "=== " + Thread.currentThread().getId() + " === " + "supplyAsync with executors", Executors.newFixedThreadPool(3));
           System.out.println("=== " + Thread.currentThread().getId() + " === " + completableFuture4.get());

           // ======== thenApply ==========
           CompletableFuture supplyAsyncFuture = CompletableFuture.supplyAsync(() -> "=== " + Thread.currentThread().getId() + " === " + "world");
           final CompletableFuture thenApplyFuture = supplyAsyncFuture
                   .thenApply(x -> "=== " + Thread.currentThread().getId() + " === " +  "Hello " + x)
                   .thenApply(y ->"=== " + Thread.currentThread().getId() + " === " +  y + ", java")
                   .thenApplyAsync(z -> "=== " + Thread.currentThread().getId() + " === " + z);
           System.out.println("=== " + Thread.currentThread().getId() + " === " + thenApplyFuture.get());

           // ======== thenAccept ==========
           final CompletableFuture thenAcceptFuture = supplyAsyncFuture.thenAccept(x -> System.out.println("==== " + x + " ======"));
           thenAcceptFuture.get();

           // ======== thenRun ==========
           final CompletableFuture thenRunFuture = supplyAsyncFuture.thenRun(() -> System.out.println("thenRun can not visit future's result."));
           thenRunFuture.get();

           // ======== thenCompose ==========
           final CompletableFuture<CompletableFuture<String>> nameInfoFuture = getName().thenApply(name -> getAge(name));
           System.out.println(nameInfoFuture.get().get());
           final CompletableFuture<String> thenCompose = getName().thenCompose(CompletableFutureTests::getAge);
           System.out.println(thenCompose.get());*/

//           final CompletableFuture<String> thenCombine = getName().thenCombine(getAge(), (x, y) -> x + y);
//           System.out.println(thenCombine.join());

           final int age = 11;
           CompletableFuture<String> handleFuture = CompletableFuture.supplyAsync(() -> {
               if (age == 0 ) {
                   throw new IllegalArgumentException("age = 0");
               }else {
                   return "age = " + age;
               }
           });

           final CompletableFuture<String> handle = handleFuture.handle((fn, ex) -> {
               if (ex != null) {
                   System.out.println(ex.getMessage());
                   return "Error occurred";
               }
               return fn;
           });

           System.out.println(handle.get());

       }



       public static CompletableFuture<String> getName(){
           return CompletableFuture.supplyAsync(() -> "name: Eric; ");
       }

       public static CompletableFuture<String> getAge() {
           return CompletableFuture.supplyAsync(() -> "age: 18");
       }
}
