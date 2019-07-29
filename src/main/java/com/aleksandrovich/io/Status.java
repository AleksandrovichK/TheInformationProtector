package com.aleksandrovich.io;

/**
 * All possible file/condition statuses
 * @author AleksandrovichK
 */
public enum Status {
    FILE_IS_EMPTY,
    FILE_CONTAINS_WRONG_INFO,
    FILE_IS_CORRUPTED,
    FILE_IS_NOT_AVAILABLE,
    FILE_CRYPTED_IS_NOT_AVAILABLE,
    FILE_DECRYPTED_IS_NOT_AVAILABLE,
    ERROR_WHILE_EXECUTING,
    SUCCESS
}
