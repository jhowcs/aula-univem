package com.univem.aula01_login;

import android.os.Parcel;
import android.os.Parcelable;

public class Produto implements Parcelable {
    private String descricao;
    private double valor;

    public Produto(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    protected Produto(Parcel in) {
        descricao = in.readString();
        valor = in.readDouble();
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(descricao);
        parcel.writeDouble(valor);
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }
}
