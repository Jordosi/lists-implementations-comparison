package org.vsu.amm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Класс для сравнения производительности ArrayList и LinkedList
 * при выполнении операций добавления, получения и удаления элементов.
 */
public class ListsComparisonTest {
    /** Количество операций для тестирования производительности. */
    private static final int OPERATIONS_COUNT = 10000;

    /**
     * Точка входа в приложение.
     * Выполняет тестирование производительности ArrayList и LinkedList.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        System.out.println("Method | Collection | Operations | Time (ms)");
        System.out.println("------------------------------------------------");

        testListPerformance(new ArrayList<>(), "ArrayList");
        testListPerformance(new LinkedList<>(), "LinkedList");
    }

    /**
     * Выполняет тестирование производительности для указанного списка.
     *
     * @param list список для тестирования
     * @param type тип коллекции (ArrayList или LinkedList)
     */
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

    /**
     * Выводит результаты тестирования в консоль.
     *
     * @param method название метода (операции)
     * @param listType тип коллекции
     * @param timeNano время выполнения в наносекундах
     */
    private static void printResult(String method, String listType, long timeNano) {
        System.out.printf("%s | %s | %d | %.3f ms%n", method, listType, OPERATIONS_COUNT, timeNano / 1_000_000.0);
    }

    /**
     * Unit-тест: проверка производительности операции добавления элементов.
     */
    @Test
    void testAddPerformance() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = measureAddTime(arrayList);
        long linkedListTime = measureAddTime(linkedList);

        assertTrue(arrayListTime > 0);
        assertTrue(linkedListTime > 0);
    }

    /**
     * Unit-тест: проверка производительности операции получения элементов.
     */
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

    /**
     * Unit-тест: проверка производительности операции удаления элементов.
     */
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

    /**
     * Измеряет время выполнения операции добавления элементов в список.
     *
     * @param list список для добавления элементов
     * @return время выполнения в наносекундах
     */
    private long measureAddTime(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            list.add(i);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Измеряет время выполнения операции получения элементов из списка.
     *
     * @param list список для получения элементов
     * @return время выполнения в наносекундах
     */
    private long measureGetTime(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            list.get(i);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Измеряет время выполнения операции удаления элементов из списка.
     *
     * @param list список для удаления элементов
     * @return время выполнения в наносекундах
     */
    private long measureDeleteTime(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = OPERATIONS_COUNT - 1; i >= 0; i--) {
            list.remove(i);
        }
        return System.nanoTime() - startTime;
    }

    /**
     * Заполняет список элементами от 0 до OPERATIONS_COUNT.
     *
     * @param list заполненный и готовый к работе список
     */
    private void fillList(List<Integer> list) {
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            list.add(i);
        }
    }
}
