package com.vdoichev;

import com.vdoichev.db.MyDbForAbpDao;
import com.vdoichev.utils.impl.EquipmentParser;
import com.vdoichev.utils.impl.MarkParser;
import com.vdoichev.utils.impl.MarketParser;
import com.vdoichev.utils.impl.ModelParser;

import java.util.List;

public class MyDbForAbp {
    public static void saveMarksToDB(List<MarkParser> marks) {
        for (MarkParser mark : marks) {
            mark.setId(MyDbForAbpDao.getIdByMark(mark));
            if (mark.getId() > 0 || MyDbForAbpDao.addMark(mark)) {
                saveMarketsToDB(mark.getMarkets(), mark.getId());
            } else {
                System.out.println("Сталася помилка при збереженні марки авто " +
                        mark.getName() + "!");
            }
        }
    }

    public static void saveMarketsToDB(List<MarketParser> markets, int mark_id) {
        for (MarketParser market : markets) {
            if (!market.isEmpty()) {
                market.setId(MyDbForAbpDao.getIdByMarket(market));
                if (market.getId() > 0 || MyDbForAbpDao.addMarket(market)) {
                    saveModelsToDB(market.getModels(), mark_id, market.getId());
                } else {
                    System.out.println("Сталася помилка при збереженні ринку збуту " +
                            market.getName() + " або він вже існує!");
                }
            }
        }
    }

    public static void saveModelsToDB(List<ModelParser> models, Integer mark_id, Integer market_id) {
        for (ModelParser model : models) {
            model.setId(MyDbForAbpDao.getIdByModel(model, mark_id, market_id));
            if (model.getId() > 0 || MyDbForAbpDao.addModel(model, mark_id, market_id)) {
                saveEquipmentsToDB(model.getEquipments(),model.getId());
            } else {
                System.out.println("Сталася помилка при збереженні моделі авто " +
                        model.getName() + "!");
            }
        }
    }

    public static void saveEquipmentsToDB(List<EquipmentParser> equipments, Integer model_id) {
        for (EquipmentParser equipment : equipments) {
            equipment.setId(MyDbForAbpDao.getIdByEquipment(equipment,model_id));
            if (equipment.getId() > 0 || MyDbForAbpDao.addEquipmeent(equipment, model_id)) {
                System.out.println("Тут можна продовжити парсинг подальших сторінок!!!");
            } else {
                System.out.println("Сталася помилка при збереженні комплектації авто " +
                        equipment.getCode() + "!");
            }
        }
    }

}
