package dsa.eetac.upc.edu.calculadora.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 25/05/17.
 */

public class Operation implements Parcelable {

    private static int lastId = 1;

    @Override
    public String toString() {
        return super.toString();
    }

    private Integer id;
    private Integer first;
    private Integer second;
    private Integer result;
    private int type;

    public Operation(int first, int second, int type) {
        this.first = first;
        this.second = second;
        this.type = type;
        this.id = lastId;
        lastId++;
    }

    public Integer getFirst() {
        return first;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public String getTypeSymbol() {
        String symbol = null;
        switch (type) {
            case 0:
                symbol = "+";
                break;
            case 1:
                symbol = "-";
                break;
            case 2:
                symbol = "*";
                break;
            case 3:
                symbol = "/";
                break;
        }
        return symbol;
    }

    public void setType(int type) {
        this.type = type;
    }

    // Parcelable implementation Overriding

    private Operation(Parcel in) {
        id = in.readInt();
        first = in.readInt();
        second = in.readInt();
        result = in.readInt();
        type = in.readInt();
    }

    public static final Creator<Operation> CREATOR = new Creator<Operation>() {
        @Override
        public Operation createFromParcel(Parcel in) {
            return new Operation(in);
        }

        @Override
        public Operation[] newArray(int size) {
            return new Operation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(first);
        dest.writeInt(second);
        dest.writeInt(result);
        dest.writeInt(type);
    }


}
