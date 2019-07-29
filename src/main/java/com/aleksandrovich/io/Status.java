package com.aleksandrovich.io;

/**
 * All possible file/condition statuses
 * @author AleksandrovichK
 */
public enum Status {
    REGISTRY_CONTAINS_WRONG_INFO,
    REGISTRY_SEGMENT_IS_NOT_AVAILABLE,
    REGISTRY_SEGMENT_IS_EMPTY,

    FILE_IS_NOT_AVAILABLE,
    FILE_IS_TOO_LONG,
    FILE_DECRYPTED_IS_NOT_AVAILABLE,

    CRYPTED_DATA_IS_NOT_AVAILABLE,

    ERROR_WHILE_EXECUTING,

    SUCCESS
}
