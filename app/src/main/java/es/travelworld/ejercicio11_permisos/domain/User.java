package es.travelworld.ejercicio11_permisos.domain;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String name;
    private String lastname;
    private String ageGroup;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.lastname);
        dest.writeString(this.ageGroup);
        dest.writeString(this.password);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.lastname = source.readString();
        this.ageGroup = source.readString();
        this.password = source.readString();
    }

    public User() {
    }

    public User(String name, String lastname, String ageGroup, String password) {
        this.name = name;
        this.lastname = lastname;
        this.ageGroup = ageGroup;
        this.password = password;
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.lastname = in.readString();
        this.ageGroup = in.readString();
        this.password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
