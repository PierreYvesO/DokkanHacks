package model;

public class Character {

    KiType type;

    public Character() {
        getType();

    }


    KiType getType() {
        return type;
    }

    void setType(KiType type) {
        this.type = type;
    }

    void setType() {

    }

    public void swap(Character character) {
        KiType tmp = character.getType();
        character.setType(this.getType());
        this.setType(tmp);
    }

    public void update() {

    }

    public void getKi() {

    }

}
