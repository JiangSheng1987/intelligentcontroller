package com.example.kdyzm.intelligenceremotecontroller.consts;

public enum Type {
    PLAYORSTOP(0),
    NEXT(1),
    BEFORE(2),
    VOICEUP(3),
    VOICEDOWN(4),
    SHUTDOWN(5),
    SHUTDOWN_CANCEL(6),
    SYSTEM_VOICE_DOWN(7),
    SYSTEM_VOICE_UP(8),
    SYSTEM_VOICE_MUTE(9),
    ;

    Type(int value) {
        this.value = value;
    }

    private int value;

    public int value() {
        return value;
    }

    public static Type get(int value){
        Type type=null;
        System.out.println(value);
        switch (value){
            case 0:
                type=Type.PLAYORSTOP;
                break;
            case 1:
                type=Type.NEXT;
                break;
            case 2:
                type=Type.BEFORE;
                break;
            case 3:
                type=Type.VOICEUP;
                break;
            case 4:
                type=Type.VOICEDOWN;
                break;
            case 5:
                type=Type.SHUTDOWN;
                break;
            case 6:
                type=Type.SHUTDOWN_CANCEL;
                break;
            case 7:
                type=Type.SYSTEM_VOICE_DOWN;
                break;
            case 8:
                type=Type.SYSTEM_VOICE_UP;
                break;
            case 9:
                type=Type.SYSTEM_VOICE_MUTE;
                break;
        }
        return type;
    }
}