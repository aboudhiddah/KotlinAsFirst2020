@file:Suppress("UNUSED_PARAMETER")

package lesson1.task1

import kotlin.math.*
import java.io.File
import java.lang.StringBuilder

// Урок 1: простые функции
// Максимальное количество баллов = 5
// Рекомендуемое количество баллов = 4

/**
 * Пример
 *
 * Вычисление квадрата целого числа
 */
fun sqr(x: Int) = x * x

/**
 * Пример
 *
 * Вычисление квадрата вещественного числа
 */
fun sqr(x: Double) = x * x

/**
 * Пример
 *
 * Вычисление дискриминанта квадратного уравнения
 */
fun discriminant(a: Double, b: Double, c: Double) = sqr(b) - 4 * a * c

/**
 * Пример
 *
 * Поиск одного из корней квадратного уравнения
 */
fun quadraticEquationRoot(a: Double, b: Double, c: Double): Double = (-b + sqrt(discriminant(a, b, c))) / (2 * a)

/**
 * Пример
 *
 * Поиск произведения корней квадратного уравнения
 */
fun quadraticRootProduct(a: Double, b: Double, c: Double): Double {
    val sd = sqrt(discriminant(a, b, c))
    val x1 = (-b + sd) / (2 * a)
    val x2 = (-b - sd) / (2 * a)
    return x1 * x2 // Результат
}


/**
 * Тривиальная (3 балла).
 *
 * Задача имеет повышенную стоимость как первая в списке.
 *
 * Пользователь задает время в часах, минутах и секундах, например, 8:20:35.
 * Рассчитать время в секундах, прошедшее с начала суток (30035 в данном случае).
 */
fun seconds(hours: Int, minutes: Int, seconds: Int): Int =
    seconds + (minutes * 60) + (hours * 3600)

/**
 * Тривиальная (1 балл)
 *
 * Пользователь задает длину отрезка в саженях, аршинах и вершках (например, 8 саженей 2 аршина 11 вершков).
 * Определить длину того же отрезка в метрах (в данном случае 18.98).
 * 1 сажень = 3 аршина = 48 вершков, 1 вершок = 4.445 см.
 */
fun lengthInMeters(sagenes: Int, arshins: Int, vershoks: Int): Double =
    ((sagenes * 3 + arshins) * 16 + vershoks) * 4.445 / 100

/**
 * Тривиальная (1 балл)
 *
 * Пользователь задает угол в градусах, минутах и секундах (например, 36 градусов 14 минут 35 секунд).
 * Вывести значение того же угла в радианах (например, 0.63256).
 */
fun angleInRadian(deg: Int, min: Int, sec: Int): Double =
    (deg + (min / 60.0) + (sec / 3600.0)) * PI / 180.0

/**
 * Тривиальная (1 балл)
 *
 * Найти длину отрезка, соединяющего точки на плоскости с координатами (x1, y1) и (x2, y2).
 * Например, расстояние между (3, 0) и (0, 4) равно 5
 */
fun trackLength(x1: Double, y1: Double, x2: Double, y2: Double): Double =
    sqrt(sqr(x2 - x1) + sqr(y2 - y1))

/**
 * Простая (2 балла)
 *
 * Пользователь задает целое число, большее 100 (например, 3801).
 * Определить третью цифру справа в этом числе (в данном случае 8).
 */
fun thirdDigit(number: Int): Int =
    (number / 100) % 10

/**
 * Простая (2 балла)
 *
 * Поезд вышел со станции отправления в h1 часов m1 минут (например в 9:25) и
 * прибыл на станцию назначения в h2 часов m2 минут того же дня (например в 13:01).
 * Определите время поезда в пути в минутах (в данном случае 216).
 */
fun travelMinutes(hoursDepart: Int, minutesDepart: Int, hoursArrive: Int, minutesArrive: Int): Int =
    (hoursArrive - hoursDepart) * 60 + (minutesArrive - minutesDepart)

/**
 * Простая (2 балла)
 *
 * Человек положил в банк сумму в s рублей под p% годовых (проценты начисляются в конце года).
 * Сколько денег будет на счету через 3 года (с учётом сложных процентов)?
 * Например, 100 рублей под 10% годовых превратятся в 133.1 рубля
 */
fun accountInThreeYears(initial: Int, percent: Int): Double =
    initial * (percent * 0.01 + 1).pow(3)

/**
 * Простая (2 балла)
 *
 * Пользователь задает целое трехзначное число (например, 478).
 * Необходимо вывести число, полученное из заданного перестановкой цифр в обратном порядке (например, 874).
 */
fun numberRevert(number: Int): Int = (number % 10) * 100 + (number % 100 / 10) * 10 + (number / 100)


/**Есть файл, в котором схематично изображено поле для игры в крестики-нолики на доске 15х15, а именно:
 *- 15 строк
 *- в каждой строке строго 15 символов
 *- пустая клетка обозначается -, крестик х, нолик о
 *

 *Функция, которую нужно написать, принимает как параметры имя этого файла и знак (крестики или нолики).
 * Необходимо определить, имеется ли на поле линия из 5 заданных знаков подряд (по вертикали, горизонтали или диагонали)
 * вернуть true, если она есть, или false, если её нет.
 *
 * */

fun ticTacToe(fileName: String, symbol: Char): Boolean {
    val fileLines = File(fileName).readLines()
    val winner = StringBuilder()
    for (i in 0..4) {
        winner.append(symbol)
    }

    for (range in fileLines) {
        if (winner in range) {
            return true
        }
        var nowString = StringBuilder()
        for (index in 0..14) {
            for (rangeF in fileLines) {
                nowString.append(rangeF[index])
            }
            if (winner in nowString) return true
            nowString = StringBuilder()
        }
        for (x in 0..10) {
            for (y in 0..10) {
                for (i in 0..4) {
                    nowString.append(fileLines[x + i][y + i])
                }
                if (winner in nowString) return true
                nowString = StringBuilder()
            }
        }
        for (x in 4..14) {
            for (y in 0..10) {
                for (i in 0..4)
                    nowString.append(fileLines[x - i][y + i])
            }
            if (winner in nowString) return true
            nowString = StringBuilder()
        }
    }

    return false

}


fun main() {
    println(ticTacToe("input/ticTacTest.txt", 'o'))

}


