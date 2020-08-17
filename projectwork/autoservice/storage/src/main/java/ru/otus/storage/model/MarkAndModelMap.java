package ru.otus.storage.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MarkAndModelMap extends LinkedHashMap<String, MarkAndModelMap.MarkValue> {
    private static final String MONDEO = "MO";
    private static final String MUSTANG = "MG";
    private static final String FOCUS = "FS";
    private static final String KUGA = "KA";
    private static final String FUSION = "FN";
    private static final String FIESTA = "FA";
    private static final String EXPLORER = "ER";

    private static final String SOLARIS = "SS";
    private static final String ELANTRA = "EA";
    private static final String SONATA = "SA";
    private static final String TUCSON = "TN";

    private static final MarkAndModelMap ourInstance = new MarkAndModelMap();

    public static MarkAndModelMap getInstance() {
        return ourInstance;
    }

    @Getter
    @Setter
    public static class MarkValue {
        private MarkType markType;
        private Map<String, ModelType> modelTypes;
    }

    private MarkAndModelMap() {
        MarkValue markValue = new MarkValue();
        markValue.markType = MarkType.FORD;
        markValue.modelTypes = new HashMap<>();
        markValue.modelTypes.put(MONDEO, ModelType.MONDEO);
        markValue.modelTypes.put(MUSTANG, ModelType.MUSTANG);
        markValue.modelTypes.put(FOCUS, ModelType.FOCUS);
        markValue.modelTypes.put(KUGA, ModelType.KUGA);
        markValue.modelTypes.put(FUSION, ModelType.FUSION);
        markValue.modelTypes.put(FIESTA, ModelType.FIESTA);
        markValue.modelTypes.put(EXPLORER, ModelType.EXPLORER);
        put("F", markValue);

        markValue = new MarkValue();
        markValue.markType = MarkType.HYUNDAI;
        markValue.modelTypes = new HashMap<>();
        markValue.modelTypes.put(SOLARIS, ModelType.SOLARIS);
        markValue.modelTypes.put(ELANTRA, ModelType.ELANTRA);
        markValue.modelTypes.put(SONATA, ModelType.SONATA);
        markValue.modelTypes.put(TUCSON, ModelType.TUCSON);
        put("H", markValue);

        markValue = new MarkValue();
        markValue.markType = MarkType.VOLKSWAGEN;
        markValue.modelTypes = new HashMap<>();
        markValue.modelTypes.put("BE", ModelType.BEETLE);
        markValue.modelTypes.put("GF", ModelType.GOLF);
        markValue.modelTypes.put("PO", ModelType.POLO);
        markValue.modelTypes.put("TN", ModelType.TIGUAN);
        markValue.modelTypes.put("TG", ModelType.TOUAREG);
        markValue.modelTypes.put("TR", ModelType.TRANSPORTER);
        put("V", markValue);

        markValue = new MarkValue();
        markValue.markType = MarkType.TOYOTA;
        markValue.modelTypes = new HashMap<>();
        markValue.modelTypes.put("CA", ModelType.COROLLA);
        markValue.modelTypes.put("CY", ModelType.CAMRY);
        markValue.modelTypes.put("R4", ModelType.RAV4);
        markValue.modelTypes.put("LR", ModelType.LAND_CRUISER);
        markValue.modelTypes.put("HX", ModelType.HILUX);
        put("T", markValue);

    }
}
