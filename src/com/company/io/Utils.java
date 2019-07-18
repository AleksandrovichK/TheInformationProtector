package com.company.io;

public class Utils {
    public static String toSubstitute(String text) {
        StringBuilder substituted = new StringBuilder();

        for (int i = 0; i < text.length(); i++)
            switch (text.charAt(i)) {
                case ('a'): {
                    substituted.append('z');
                    break;
                }
                case ('b'): {
                    substituted.append('y');
                    break;
                }
                case ('c'): {
                    substituted.append('x');
                    break;
                }
                case ('d'): {
                    substituted.append('w');
                    break;
                }
                case ('e'): {
                    substituted.append('v');
                    break;
                }
                case ('f'): {
                    substituted.append('u');
                    break;
                }
                case ('g'): {
                    substituted.append('t');
                    break;
                }
                case ('h'): {
                    substituted.append('s');
                    break;
                }
                case ('i'): {
                    substituted.append('r');
                    break;
                }
                case ('j'): {
                    substituted.append('q');
                    break;
                }
                case ('k'): {
                    substituted.append('p');
                    break;
                }
                case ('l'): {
                    substituted.append('o');
                    break;
                }
                case ('m'): {
                    substituted.append('n');
                    break;
                }
                case ('n'): {
                    substituted.append('m');
                    break;
                }
                case ('o'): {
                    substituted.append('l');
                    break;
                }
                case ('p'): {
                    substituted.append('k');
                    break;
                }
                case ('q'): {
                    substituted.append('j');
                    break;
                }
                case ('r'): {
                    substituted.append('i');
                    break;
                }
                case ('s'): {
                    substituted.append('h');
                    break;
                }
                case ('t'): {
                    substituted.append('g');
                    break;
                }
                case ('u'): {
                    substituted.append('f');
                    break;
                }
                case ('v'): {
                    substituted.append('e');
                    break;
                }
                case ('w'): {
                    substituted.append('d');
                    break;
                }
                case ('x'): {
                    substituted.append('c');
                    break;
                }
                case ('y'): {
                    substituted.append('b');
                    break;
                }
                case ('z'): {
                    substituted.append('a');
                    break;
                }

                case ('A'): {
                    substituted.append('Z');
                    break;
                }
                case ('B'): {
                    substituted.append('Y');
                    break;
                }
                case ('C'): {
                    substituted.append('X');
                    break;
                }
                case ('D'): {
                    substituted.append('W');
                    break;
                }
                case ('E'): {
                    substituted.append('V');
                    break;
                }
                case ('F'): {
                    substituted.append('U');
                    break;
                }
                case ('G'): {
                    substituted.append('T');
                    break;
                }
                case ('H'): {
                    substituted.append('S');
                    break;
                }
                case ('I'): {
                    substituted.append('R');
                    break;
                }
                case ('J'): {
                    substituted.append('Q');
                    break;
                }
                case ('K'): {
                    substituted.append('P');
                    break;
                }
                case ('L'): {
                    substituted.append('O');
                    break;
                }
                case ('M'): {
                    substituted.append('N');
                    break;
                }
                case ('N'): {
                    substituted.append('M');
                    break;
                }
                case ('O'): {
                    substituted.append('L');
                    break;
                }
                case ('P'): {
                    substituted.append('K');
                    break;
                }
                case ('Q'): {
                    substituted.append('J');
                    break;
                }
                case ('R'): {
                    substituted.append('I');
                    break;
                }
                case ('S'): {
                    substituted.append('H');
                    break;
                }
                case ('T'): {
                    substituted.append('G');
                    break;
                }
                case ('U'): {
                    substituted.append('F');
                    break;
                }
                case ('V'): {
                    substituted.append('E');
                    break;
                }
                case ('W'): {
                    substituted.append('D');
                    break;
                }
                case ('X'): {
                    substituted.append('C');
                    break;
                }
                case ('Y'): {
                    substituted.append('B');
                    break;
                }
                case ('Z'): {
                    substituted.append('A');
                    break;
                }

                case ('0'): {
                    substituted.append('!');
                    break;
                }
                case ('1'): {
                    substituted.append('"');
                    break;
                }
                case ('2'): {
                    substituted.append('+');
                    break;
                }
                case ('3'): {
                    substituted.append(';');
                    break;
                }
                case ('4'): {
                    substituted.append('%');
                    break;
                }
                case ('5'): {
                    substituted.append(':');
                    break;
                }
                case ('6'): {
                    substituted.append('?');
                    break;
                }
                case ('7'): {
                    substituted.append('*');
                    break;
                }
                case ('8'): {
                    substituted.append('(');
                    break;
                }
                case ('9'): {
                    substituted.append(')');
                    break;
                }
                case ('='): {
                    substituted.append(' ');
                    break;
                }

                case ('!'): {
                    substituted.append('0');
                    break;
                }
                case ('"'): {
                    substituted.append('1');
                    break;
                }
                case ('+'): {
                    substituted.append('2');
                    break;
                }
                case (';'): {
                    substituted.append('3');
                    break;
                }
                case ('%'): {
                    substituted.append('4');
                    break;
                }
                case (':'): {
                    substituted.append('5');
                    break;
                }
                case ('?'): {
                    substituted.append('6');
                    break;
                }
                case ('*'): {
                    substituted.append('7');
                    break;
                }
                case ('('): {
                    substituted.append('8');
                    break;
                }
                case (')'): {
                    substituted.append('9');
                    break;
                }
                case (' '): {
                    substituted.append('=');
                    break;
                }

                default: {
                    substituted.append('-');
                    break;
                }
            }
        return substituted.toString();
    }
}
