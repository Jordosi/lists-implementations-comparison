package org.vsu.amm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListsComparisonTest {
    private static final int OPERATIONS_COUNT = 10000;

    public static void main(String[] args) {
        System.out.println("Method | Collection | Operations | Time (ms)");
        System.out.println("------------------------------------------------");

        testListPerformance(new ArrayList<>(), "ArrayList");
        testListPerformance(new LinkedList<>(), "LinkedList");
    }

    private static void testListPerformance(List<Integer> list, String type){
        long startTime, endTime;

        startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            list.add(i);
        }
        endTime = System.nanoTime();
        printResult("Add", type, endTime - startTime);

        startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            list.get(i);
        }
        endTime = System.nanoTime();
        printResult("Get", type, endTime - startTime);

        startTime = System.nanoTime();
        for (int i = OPERATIONS_COUNT - 1; i >= 0; i--) {
            list.remove(i);
        }
        endTime = System.nanoTime();

        printResult("Remove", type, endTime - startTime);
    }

    private static void printResult(String method, String listType, long timeNano) {
        System.out.printf("%s | %s | %d | %.3f ms\n", method, listType, OPERATIONS_COUNT, timeNano / 1_000_000.0);
    }

    @Test
    void testAddPerformance() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = measureAddTime(arrayList);
        long linkedListTime = measureAddTime(linkedList);

        assertTrue(arrayListTime > 0);
        assertTrue(linkedListTime > 0);
    }

    @Test
    void testGetPerformance() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        fillList(arrayList);
        fillList(linkedList);

        long arrayListTime = measureGetTime(arrayList);
        long linkedListTime = measureGetTime(linkedList);

        assertTrue(arrayListTime > 0);
        assertTrue(linkedListTime > 0);
    }

    @Test
    void testDeletePerformance() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        fillList(arrayList);
        fillList(linkedList);

        long arrayListTime = measureDeleteTime(arrayList);
        long linkedListTime = measureDeleteTime(linkedList);

        assertTrue(arrayListTime > 0);
        assertTrue(linkedListTime > 0);
    }

    private long measureAddTime(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            list.add(i);
        }
        return System.nanoTime() - startTime;
    }

    private long measureGetTime(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            list.get(i);
        }
        return System.nanoTime() - startTime;
    }

    private long measureDeleteTime(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = OPERATIONS_COUNT - 1; i >= 0; i--) {
            list.remove(i);
        }
        return System.nanoTime() - startTime;
    }

    private void fillList(List<Integer> list) {
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            list.add(i);
        }
    }
}