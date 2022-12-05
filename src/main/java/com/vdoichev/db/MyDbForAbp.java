package com.vdoichev.db;

import com.vdoichev.utils.impl.MarkParser;
import com.vdoichev.utils.impl.MarketParser;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MyDbForAbp {
    public static void saveMarksToDB(List<MarkParser> marks) {
        for (MarkParser mark : marks) {
            mark.setId(getIdByMark(mark));
            if (mark.getId() > 0 || addMark(mark)) {
                saveMarketsToDB(mark.getMarkets());
            } else {
                System.out.println("Сталася помилка при збереженні марки авто " +
                        mark.getName() + "!");
            }
        }
    }

    public static void saveMarketsToDB(List<MarketParser> markets) {
        for (MarketParser market : markets) {
            if (!market.isEmpty()) {
                market.setId(getIdByMarket(market));
                if (market.getId() > 0 || addMarket(market)) {
//                    System.out.println("Додавання ринку збуту " +
//                            market.getName() + "!");
                } else {
                    System.out.println("Сталася помилка при збереженні ринку збуту " +
                            market.getName() + " або він вже існує!");
                }
            }
        }
    }

    private static int getIdByMarket(MarketParser marketParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            System.out.print("Перевірка наявності в БД марки авто " +
                    marketParser.getName());
            String sql = "select id from market where code = ?";
            PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(sql);
            statement.setString(1, marketParser.getCode());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                System.out.println(" - знайдено запис id = " + rs.getInt("id"));
                return rs.getInt("id");
            } else System.out.println(" - не знайдено");
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при перевірці наявності в БД марки авто " +
                    marketParser.getName());
        }
        return 0;
    }

    public static int getIdByMark(MarkParser markParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            System.out.print("Перевірка наявності в БД марки авто " +
                    markParser.getName());
            String sql = "select id from mark where name = ?";
            PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(sql);
            statement.setString(1, markParser.getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                System.out.println(" - знайдено запис id = " + rs.getInt("id"));
                return rs.getInt("id");
            } else System.out.println(" - не знайдено");
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при перевірці наявності в БД марки авто " +
                    markParser.getName());
        }
        return 0;
    }

    public static boolean addMark(MarkParser markParser) {
        try (Connection con = MySqlConnection.getConnection()) {

            String sql = "INSERT INTO mark(name, catalogGroup) values (?, ?)";
            PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, markParser.getName());
            statement.setString(2, markParser.getCatalogGroup());
            int resultUpdate = statement.executeUpdate();
            if (resultUpdate > 0) {
                System.out.println("Успішне додавання марки авто " +
                        markParser.getName() + "!");
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    markParser.setId(id);
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при додаванні в БД марки авто " +
                    markParser.getName());
        }
        return false;
    }

    public static boolean addMarket(MarketParser marketParser) {
        try (Connection con = MySqlConnection.getConnection()) {
            String sql = "INSERT INTO market(code, name) values (?, ?)";
            PreparedStatement statement = Objects.requireNonNull(con).prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, marketParser.getCode());
            statement.setString(2, marketParser.getName());
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("Успішне додавання ринку збуту " +
                        marketParser.getName() + "!");
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    marketParser.setId(id);
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Виникла помилка при додаванні в БД ринку збуту " +
                    marketParser.getName());
        }
        return false;
    }
}
