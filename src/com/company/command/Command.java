package com.company.command;

import com.company.exception.InterruptOperationException;

/**
 * Created by Roman on 20.07.2014.
 */
interface Command {
    void execute() throws InterruptOperationException;
}
