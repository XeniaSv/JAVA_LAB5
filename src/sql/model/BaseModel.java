package sql.model;

import java.util.Objects;

//Класс базовой модели для sql
public class BaseModel {
    //ID продукта
    protected int id;

    //Перегруженный метод equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseModel baseModel = (BaseModel) o;
        return id == baseModel.id;
    }

    //Перегруженный метод hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
