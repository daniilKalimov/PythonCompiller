package com.itransition.rpcserver.contract;

import com.itransition.rpcserver.dto.RemoteCall;

import java.util.List;

/**
 * Remote script call interface
 */
public interface RemoteScript {
    /**
     * List available functions (units)
     * @return list of units names
     */
    List<String> enumerateFunctions();

    /**
     *
     * @param call {@link RemoteCall} instance for parameters of call
     * @param <T> expected result call type
     * @return
     */
    <T> T execute(RemoteCall<T> call);
}
