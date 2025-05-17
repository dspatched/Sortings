package com.dspatched;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestGenerics {

    public static class Task<T> {
        private int id;

        public Task() {
//            class ContainerTypeFromTypeToken extends TypeToken<T> {}
//            var container = new ContainerTypeFromTypeToken();
//            ParameterizedType type = (ParameterizedType) container.getType();
//            Type actualTypeArgument = type.getActualTypeArguments()[0];
            System.out.println();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        Task<String> stringTask = new Task<String>();
    }
}

abstract class TypeToken<T> {
    private Type type;

    protected TypeToken() {
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}
