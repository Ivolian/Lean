/*
 * Copyright 2016 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ivotai.lean.user.n

import com.ivotai.lean.user.po.User


/**
 * The View state for the Menu
 * @author Hannes Dorfmann
 */
interface UserViewState {

    /**
     * Loads the list of all menu items
     */
    class LoadingState : UserViewState {

        override fun toString(): String {
            return "LoadingState{}"
        }
    }

    /**
     * Ane error has ocurred while loading the data
     */
    class ErrorState(private val error: Throwable) : UserViewState {

        override fun toString(): String {
            return "ErrorState{" +
                    "error=" + error +
                    '}'
        }
    }

    /**
     * Data has been loaded successfully and can now be displayed
     */
    class DataState( val users: List<User>) : UserViewState {

        override fun toString(): String {
            return "DataState{" +
                    "users=" + users +
                    '}'
        }
    }

}
