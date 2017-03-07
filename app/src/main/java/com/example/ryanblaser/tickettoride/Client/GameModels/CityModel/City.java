package com.example.ryanblaser.tickettoride.Client.GameModels.CityModel;

import android.graphics.PointF;
import android.graphics.RectF;

import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 4/03/17.
 */

public class City implements  iCity{

    public City(String name, PointF p, Map<String, List<Route>> m){
        _S_name = name;
        _P_coordinate = p;
        _M_Routes = m;
    }

    //------------------------------------CLASS VARIABLES----------------------------------------//
    private String _S_name = null;
    private PointF _P_coordinate = null;
    private Map<String, List<Route>> _M_Routes = new HashMap<>();
    private List<City> _L_adjacentCities = new ArrayList<>();
    private RectF _cityPointArea;



    //------------------------------------STATIC VARIABLES---------------------------------------//
    /** Used for the click listeners */
    private static Boolean _B_areCitiesSet = false;
    public static List<City> _L_allCities = new ArrayList();
    public static Map<String, City> _M_allCities = new HashMap<>();



    //------------------------------------GETTERS AND SETTERS------------------------------------//
    public String get_S_name() { return _S_name; }
    public void set_S_name(String name) { _S_name = name; }

    public PointF get_location() { return _P_coordinate; }
    public void set_location(PointF coordinate) { _P_coordinate = coordinate; }

    public Map<String, List<Route>> get_M_Routes() { return _M_Routes; }
    public void set_M_Routes(Map<String, List<Route>> routes) { _M_Routes = routes; }

    public List<City> get_adjacentCities() { return _L_adjacentCities; }
    public void set_adjacentCities(List<City> adjacentCities) { _L_adjacentCities = adjacentCities; }

    public static List<City> get_allCities() {
        return new ArrayList<City>(_M_allCities.values()); }
    public static void set_L_allCities(List<City> allCities) { _L_allCities = allCities; }


    public static Map<String, City> get_allCitiesMap() { return _M_allCities; }
    public static void set_M_allCities(Map<String, City> allCities) { _M_allCities = allCities; }

    public RectF get_cityPointArea() { return _cityPointArea; }
    public void set_cityPointArea(RectF _cityPointArea) { this._cityPointArea = _cityPointArea; }

    public PointF get_coordinate() { return _P_coordinate; }
    public void set_coordinate(PointF _P_coordinate) { this._P_coordinate = _P_coordinate; }

    //------------------------------------STATIC FUNCTIONS----------------------------------------//

    public static Boolean are_CitiesSet(){
        return _B_areCitiesSet;
    }

    public static void initAllCities(){
        String name;
        PointF p;
        List<Route> routeList;
        Map<String, List<Route>> routes;
        Route r;

        //--------------------------------Vancouver-------------------------//
        name = "Vancouver";
        p = new PointF(346, 267);
        routes = new HashMap<>();

        //Vancouver to Calgary
        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Vancouver-Calgary");
        routeList.add(r);
        routes.put("Calgary", routeList);

        //Seattle to vancouver routes
        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Seattle-Vancouver-1");
        routeList.add(r);

        r = Route.get_RoutesMap().get("Seattle-Vancouver-2");
        routeList.add(r);
        routes.put("Seattle", routeList);

        //Mapping Routes
        _M_allCities.put("Vancouver", new City(name, p, routes));

        //--------------------------------Calgary-------------------------//
        name = "Calgary";
        p = new PointF(594, 235);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Vancouver-Calgary");
        routeList.add(r);
        routes.put("Vancouver", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Seattle-Calgary");
        routeList.add(r);
        routes.put("Seattle-1", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Calgary-Helena");
        routeList.add(r);
        routes.put("Helena", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Calgary-Winnipeg");
        routeList.add(r);
        routes.put("Winnipeg", routeList);

        _M_allCities.put("Calgary", new City(name, p, routes));

        //--------------------------------Seattle-------------------------//
        name = "Seattle";
        p = new PointF(346, 368);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Seattle-Calgary");
        routeList.add(r);
        routes.put("Calgary", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Seattle-Vancouver-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Seattle-Vancouver-2");
        routeList.add(r);
        routes.put("Vancouver", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Seattle-Helena");
        routeList.add(r);
        routes.put("Helena", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Portland-Seattle-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Portland-Seattle-2");
        routeList.add(r);
        routes.put("Portland", routeList);

        _M_allCities.put("Seattle", new City(name, p, routes));

        //--------------------------------Portland-------------------------//
        name = "Portland";
        p = new PointF(304, 466);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Portland-Seattle-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Portland-Seattle-2");
        routeList.add(r);
        routes.put("Seattle", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SanFrancisco-Portland-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SanFrancisco-Portland-2");
        routeList.add(r);
        routes.put("SanFrancisco", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Portland-SaltLakeCity");
        routeList.add(r);
        routes.put("SaltLakeCity", routeList);

        _M_allCities.put("Portland", new City(name, p, routes));

        //--------------------------------San Francisco-------------------------//
        name = "SanFrancisco";
        p = new PointF(279, 827);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SanFrancisco-Portland-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SanFrancisco-Portland-2");
        routeList.add(r);
        routes.put("Portland", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SanFrancisco-SaltLakeCity-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SanFrancisco-SaltLakeCity-2");
        routeList.add(r);
        routes.put("SaltLakeCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SanFrancisco-LosAngeles-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SanFrancisco-LosAngeles-2");
        routeList.add(r);
        routes.put("LosAngeles", routeList);

        _M_allCities.put("SanFrancisco", new City(name, p, routes));


        //--------------------------------Los Angeles-------------------------//
        name = "LosAngeles";
        p = new PointF(414, 1028);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SanFrancisco-LosAngeles-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SanFrancisco-LosAngeles-2");
        routeList.add(r);
        routes.put("SanFrancisco", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LosAngeles-LasVegas");
        routeList.add(r);
        routes.put("LasVegas", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LosAngeles-Phoenix");
        routeList.add(r);
        routes.put("Phoenix", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LosAngeles-ElPaso");
        routeList.add(r);
        routes.put("ElPaso", routeList);

        _M_allCities.put("LosAngeles", new City(name, p, routes));


        //--------------------------------Las Vegas-------------------------//
        name = "LasVegas";
        p = new PointF(522, 885);
        routes = new HashMap<>();


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LasVegas-SaltLakeCity");
        routeList.add(r);
        routes.put("SaltLakeCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LosAngeles-LasVegas");
        routeList.add(r);
        routes.put("LosAngeles", routeList);

        _M_allCities.put("LasVegas", new City(name, p, routes));


        //--------------------------------Phoenix-------------------------//
        name = "Phoenix";
        p = new PointF(645, 1034);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LosAngeles-Phoenix");
        routeList.add(r);
        routes.put("LosAngeles", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Phoenix-Denver");
        routeList.add(r);
        routes.put("Denver", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Phoenix-SantaFe");
        routeList.add(r);
        routes.put("SantaFe", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Phoenix-ElPaso");
        routeList.add(r);
        routes.put("ElPaso", routeList);

        _M_allCities.put("Phoenix", new City(name, p, routes));

        //--------------------------------El Paso-------------------------//
        name = "ElPaso";
        p = new PointF(862, 1119);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LosAngeles-ElPaso");
        routeList.add(r);
        routes.put("LosAngeles", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Phoenix-ElPaso");
        routeList.add(r);
        routes.put("Phoenix", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("ElPaso-SantaFe");
        routeList.add(r);
        routes.put("SantaFe", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("ElPaso-OklahomaCity");
        routeList.add(r);
        routes.put("OklahomaCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("ElPaso-Dallas");
        routeList.add(r);
        routes.put("Dallas", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("ElPaso-Houston");
        routeList.add(r);
        routes.put("Houston", routeList);


        _M_allCities.put("ElPaso", new City(name, p, routes));

        //--------------------------------Santa Fe-------------------------//
        name = "SantaFe";
        p = new PointF(874, 937);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("ElPaso-SantaFe");
        routeList.add(r);
        routes.put("ElPaso", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SantaFe-OklahomaCity");
        routeList.add(r);
        routes.put("OklahomaCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SantaFe-Denver");
        routeList.add(r);
        routes.put("Denver", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Phoenix-SantaFe");
        routeList.add(r);
        routes.put("Phoenix", routeList);

        _M_allCities.put("SantaFe", new City(name, p, routes));

        //--------------------------------Denver-------------------------//
        name = "Denver";
        p = new PointF(889, 768);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SantaFe-Denver");
        routeList.add(r);
        routes.put("SantaFe", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Phoenix-Denver");
        routeList.add(r);
        routes.put("Phoenix", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Denver-OklahomaCity");
        routeList.add(r);
        routes.put("OklahomaCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Denver-KansasCity");
        routeList.add(r);
        routes.put("KansasCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Denver-Omaha");
        routeList.add(r);
        routes.put("Omaha", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Helena-Denver");
        routeList.add(r);
        routes.put("Helena", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaltLakeCity-Denver-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SaltLakeCity-Denver-2");
        routeList.add(r);
        routes.put("SaltLakeCity", routeList);

        _M_allCities.put("Denver", new City(name, p, routes));

        //--------------------------------Salt Lake City-------------------------//
        name = "SaltLakeCity";
        p = new PointF(645, 707);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SanFrancisco-SaltLakeCity-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SanFrancisco-SaltLakeCity-2");
        routeList.add(r);
        routes.put("SanFrancisco", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LasVegas-SaltLakeCity");
        routeList.add(r);
        routes.put("LasVegas", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Portland-SaltLakeCity");
        routeList.add(r);
        routes.put("Portland", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaltLakeCity-Helena");
        routeList.add(r);
        routes.put("Helena", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaltLakeCity-Denver-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SaltLakeCity-Denver-2");
        routeList.add(r);
        routes.put("Denver", routeList);

        _M_allCities.put("SaltLakeCity", new City(name, p, routes));

        //--------------------------------Helena-------------------------//
        name = "Helena";
        p = new PointF(781, 479);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaltLakeCity-Helena");
        routeList.add(r);
        routes.put("Helena", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Seattle-Helena");
        routeList.add(r);
        routes.put("Seattle", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Calgary-Helena");
        routeList.add(r);
        routes.put("Calgary", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Helena-Winnipeg");
        routeList.add(r);
        routes.put("Winnipeg", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Helena-Duluth");
        routeList.add(r);
        routes.put("Duluth", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Helena-KansasCity");
        routeList.add(r);
        routes.put("KansasCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Helena-Denver");
        routeList.add(r);
        routes.put("Denver", routeList);

        _M_allCities.put("Helena", new City(name, p, routes));

        //--------------------------------Winnipeg-------------------------//
        name = "Winnipeg";
        p = new PointF(1011, 255);
        routes = new HashMap<>();


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Calgary-Winnipeg");
        routeList.add(r);
        routes.put("Calgary", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Helena-Winnipeg");
        routeList.add(r);
        routes.put("Helena", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Winnipeg-Duluth");
        routeList.add(r);
        routes.put("Duluth", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Winnipeg-SaultSt.Marie");
        routeList.add(r);
        routes.put("SaultSt.Marie", routeList);

        _M_allCities.put("Winnipeg", new City(name, p, routes));


        //--------------------------------Duluth-------------------------//
        name = "Duluth";
        p = new PointF(1223, 471);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Winnipeg-Duluth");
        routeList.add(r);
        routes.put("Winnipeg", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Duluth-SaultSt.Marie");
        routeList.add(r);
        routes.put("SaultSt.Marie", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Duluth-Toronto");
        routeList.add(r);
        routes.put("Toronto", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Duluth-Chicago");
        routeList.add(r);
        routes.put("Chicago", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Omaha-Duluth-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Omaha-Duluth-2");
        routeList.add(r);
        routes.put("Omaha", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Helena-Duluth");
        routeList.add(r);
        routes.put("Helena", routeList);

        _M_allCities.put("Duluth", new City(name, p, routes));

        //--------------------------------Omaha-------------------------//
        name = "Omaha";
        p = new PointF(1167, 639);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Omaha-Duluth-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Omaha-Duluth-2");
        routeList.add(r);
        routes.put("Duluth", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Helena-Omaha");
        routeList.add(r);
        routes.put("Helena", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Omaha-Chicago");
        routeList.add(r);
        routes.put("Chicago", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Omaha-KansasCity-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Omaha-KansasCity-2");
        routeList.add(r);
        routes.put("KansasCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Denver-Omaha");
        routeList.add(r);
        routes.put("Denver", routeList);

        _M_allCities.put("Omaha", new City(name, p, routes));

        //--------------------------------Kansas City-------------------------//
        name = "KansasCity";
        p = new PointF(1206, 741);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Denver-KansasCity");
        routeList.add(r);
        routes.put("Denver", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Omaha-KansasCity-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Omaha-KansasCity-2");
        routeList.add(r);
        routes.put("Omaha", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("KansasCity-SaintLouis-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("KansasCity-SaintLouis-2");
        routeList.add(r);
        routes.put("SaintLouis", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("OklahomaCity-KansasCity-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("OklahomaCity-KansasCity-2");
        routeList.add(r);
        routes.put("OklahomaCity", routeList);

        _M_allCities.put("KansasCity", new City(name, p, routes));

        //--------------------------------Oklahoma City-------------------------//
        name = "OklahomaCity";
        p = new PointF(1167, 901);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Denver-OklahomaCity");
        routeList.add(r);
        routes.put("Denver", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("OklahomaCity-KansasCity-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("OklahomaCity-KansasCity-2");
        routeList.add(r);
        routes.put("KansasCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("OklahomaCity-LittleRock");
        routeList.add(r);
        routes.put("LittleRock", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("OklahomaCity-Dallas-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("OklahomaCity-Dallas-2");
        routeList.add(r);
        routes.put("Dallas", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("ElPaso-OklahomaCity");
        routeList.add(r);
        routes.put("ElPaso", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SantaFe-OklahomaCity");
        routeList.add(r);
        routes.put("SantaFe", routeList);

        _M_allCities.put("OklahomaCity", new City(name, p, routes));


        //--------------------------------Dallas-------------------------//
        name = "Dallas";
        p = new PointF(1206, 1060);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("ElPaso-Dallas");
        routeList.add(r);
        routes.put("ElPaso", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("OklahomaCity-Dallas-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("OklahomaCity-Dallas-2");
        routeList.add(r);
        routes.put("OklahomaCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Dallas-LittleRock");
        routeList.add(r);
        routes.put("LittleRock", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Dallas-Houston-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Dallas-Houston-2");
        routeList.add(r);
        routes.put("Houston", routeList);


        _M_allCities.put("Dallas", new City(name, p, routes));


        //--------------------------------Houston-------------------------//
        name = "Houston";
        p = new PointF(1279, 1137);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("ElPaso-Houston");
        routeList.add(r);
        routes.put("ElPaso", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Dallas-Houston-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Dallas-Houston-2");
        routeList.add(r);
        routes.put("Dallas", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Houston-NewOrleans");
        routeList.add(r);
        routes.put("NewOrleans", routeList);

        _M_allCities.put("Houston", new City(name, p, routes));

        //--------------------------------New Orleans-------------------------//
        name = "NewOrleans";
        p = new PointF(1457, 1111);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Houston-NewOrleans");
        routeList.add(r);
        routes.put("Houston", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LittleRock-NewOrleans");
        routeList.add(r);
        routes.put("LittleRock", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("NewOrleans-Atlanta-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("NewOrleans-Atlanta-2");
        routeList.add(r);
        routes.put("Atlanta", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("NewOrleans-Miami");
        routeList.add(r);
        routes.put("Miami", routeList);

        _M_allCities.put("NewOrleans", new City(name, p, routes));


        //--------------------------------Little Rock-------------------------//
        name = "LittleRock";
        p = new PointF(1338, 906);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("OklahomaCity-LittleRock");
        routeList.add(r);
        routes.put("OklahomaCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LittleRock-SaintLouis");
        routeList.add(r);
        routes.put("SaintLouis", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LittleRock-Nashville");
        routeList.add(r);
        routes.put("Nashville", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LittleRock-NewOrleans");
        routeList.add(r);
        routes.put("NewOrleans", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Dallas-LittleRock");
        routeList.add(r);
        routes.put("Dallas", routeList);


        _M_allCities.put("LittleRock", new City(name, p, routes));


        //--------------------------------Saint Louis-------------------------//
        name = "SaintLouis";
        p = new PointF(1368, 740);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("KansasCity-SaintLouis-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("KansasCity-SaintLouis-2");
        routeList.add(r);
        routes.put("KansasCity", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaintLouis-Chicago-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SaintLouis-Chicago-2");
        routeList.add(r);
        routes.put("Chicago", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaintLouis-Pittsburgh");
        routeList.add(r);
        routes.put("Pittsburgh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaintLouis-Nashville");
        routeList.add(r);
        routes.put("Nashville", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LittleRock-SaintLouis");
        routeList.add(r);
        routes.put("LittleRock", routeList);

        _M_allCities.put("SaintLouis", new City(name, p, routes));

        //--------------------------------Chicago-------------------------//
        name = "Chicago";
        p = new PointF(1445, 584);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaintLouis-Chicago-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("SaintLouis-Chicago-2");
        routeList.add(r);
        routes.put("SaintLouis", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Omaha-Chicago");
        routeList.add(r);
        routes.put("Omaha", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Duluth-Chicago");
        routeList.add(r);
        routes.put("Duluth", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Chicago-Toronto");
        routeList.add(r);
        routes.put("Toronto", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Chicago-Pittsburgh-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Chicago-Pittsburgh-2");
        routeList.add(r);
        routes.put("Pittsburgh", routeList);


        _M_allCities.put("Chicago", new City(name, p, routes));


        //--------------------------------Sault St. Marie-------------------------//
        name = "SaultSt.Marie";
        p = new PointF(1458, 341);
        routes = new HashMap<>();


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Winnipeg-SaultSt.Marie");
        routeList.add(r);
        routes.put("Winnipeg", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Duluth-SaultSt.Marie");
        routeList.add(r);
        routes.put("Duluth", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaultSt.Marie-Toronto");
        routeList.add(r);
        routes.put("Toronto", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaultSt.Marie-Montreal");
        routeList.add(r);
        routes.put("Montreal", routeList);


        _M_allCities.put("SaultSt.Marie", new City(name, p, routes));

        //--------------------------------Toronto-------------------------//
        name = "Toronto";
        p = new PointF(1664, 389);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Chicago-Toronto");
        routeList.add(r);
        routes.put("Toronto", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Duluth-Toronto");
        routeList.add(r);
        routes.put("Duluth", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaultSt.Marie-Toronto");
        routeList.add(r);
        routes.put("SaultSt.Marie", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Toronto-Montreal");
        routeList.add(r);
        routes.put("Montreal", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Toronto-Pittsburgh");
        routeList.add(r);
        routes.put("Pittsburgh", routeList);




        _M_allCities.put("Toronto", new City(name, p, routes));


        //--------------------------------Pittsburgh-------------------------//
        name = "Pittsburgh";
        p = new PointF(1692, 560);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Chicago-Pittsburgh-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Chicago-Pittsburgh-2");
        routeList.add(r);
        routes.put("Chicago", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Toronto-Pittsburgh");
        routeList.add(r);
        routes.put("Toronto", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Pittsburgh-NewYork-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Pittsburgh-NewYork-2");
        routeList.add(r);
        routes.put("NewYork", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Pittsburgh-Washington");
        routeList.add(r);
        routes.put("Washington", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Pittsburgh-Raleigh");
        routeList.add(r);
        routes.put("Raleigh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Nashville-Pittsburgh");
        routeList.add(r);
        routes.put("Nashville", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaintLouis-Pittsburgh");
        routeList.add(r);
        routes.put("SaintLouis", routeList);




        _M_allCities.put("Pittsburgh", new City(name, p, routes));


        //--------------------------------Nashville-------------------------//
        name = "Nashville";
        p = new PointF(1544, 816);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("LittleRock-Nashville");
        routeList.add(r);
        routes.put("LittleRock", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaintLouis-Nashville");
        routeList.add(r);
        routes.put("SaintLouis", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Nashville-Pittsburgh");
        routeList.add(r);
        routes.put("Pittsburgh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Nashville-Raleigh");
        routeList.add(r);
        routes.put("Raleigh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Nashville-Atlanta");
        routeList.add(r);
        routes.put("Atlanta", routeList);


        _M_allCities.put("Nashville", new City(name, p, routes));


        //--------------------------------Atlanta-------------------------//
        name = "Atlanta";
        p = new PointF(1634, 880);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Nashville-Atlanta");
        routeList.add(r);
        routes.put("Nashville", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Atlanta-Raleigh-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Atlanta-Raleigh-2");
        routeList.add(r);
        routes.put("Raleigh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Atlanta-Charleston");
        routeList.add(r);
        routes.put("Charleston", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Atlanta-Miami");
        routeList.add(r);
        routes.put("Miami", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("NewOrleans-Atlanta-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("NewOrleans-Atlanta-2");
        routeList.add(r);
        routes.put("NewOrleans", routeList);

        _M_allCities.put("Atlanta", new City(name, p, routes));

        //--------------------------------Miami-------------------------//
        name = "Miami";
        p = new PointF(1867, 1196);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("NewOrleans-Miami");
        routeList.add(r);
        routes.put("NewOrleans", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Atlanta-Miami");
        routeList.add(r);
        routes.put("Atlanta", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Charleston-Miami");
        routeList.add(r);
        routes.put("Charleston", routeList);

        _M_allCities.put("Miami", new City(name, p, routes));


        //--------------------------------Charleston-------------------------//
        name = "Charleston";
        p = new PointF(1818, 891);
        routes = new HashMap<>();

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Atlanta-Charleston");
        routeList.add(r);
        routes.put("Atlanta", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Raleigh-Charleston");
        routeList.add(r);
        routes.put("Raleigh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Charleston-Miami");
        routeList.add(r);
        routes.put("Miami", routeList);

        _M_allCities.put("Charleston", new City(name, p, routes));


        //--------------------------------Raleigh-------------------------//
        name = "Raleigh";
        p = new PointF(1757, 769);
        routes = new HashMap<>();


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Atlanta-Raleigh-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Atlanta-Raleigh-2");
        routeList.add(r);
        routes.put("Atlanta", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Nashville-Raleigh");
        routeList.add(r);
        routes.put("Nashville", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Pittsburgh-Raleigh");
        routeList.add(r);
        routes.put("Pittsburgh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Raleigh-Washington-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Raleigh-Washington-2");
        routeList.add(r);
        routes.put("Washington", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Raleigh-Charleston");
        routeList.add(r);
        routes.put("Charleston", routeList);


        _M_allCities.put("Raleigh", new City(name, p, routes));


        //--------------------------------Washington-------------------------//
        name = "Washington";
        p = new PointF(1870, 639);
        routes = new HashMap<>();


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Raleigh-Washington-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Raleigh-Washington-2");
        routeList.add(r);
        routes.put("Raleigh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Pittsburgh-Washington");
        routeList.add(r);
        routes.put("Pittsburgh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("NewYork-Washington-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("NewYork-Washington-2");
        routeList.add(r);
        routes.put("NewYork", routeList);

        _M_allCities.put("Washington", new City(name, p, routes));


        //--------------------------------New York-------------------------//
        name = "NewYork";
        p = new PointF(1852, 474);
        routes = new HashMap<>();


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("NewYork-Washington-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("NewYork-Washington-2");
        routeList.add(r);
        routes.put("Washington", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Pittsburgh-NewYork-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Pittsburgh-NewYork-2");
        routeList.add(r);
        routes.put("Pittsburgh", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Montreal-NewYork");
        routeList.add(r);
        routes.put("Montreal", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("NewYork-Boston-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("NewYork-Boston-2");
        routeList.add(r);
        routes.put("Boston", routeList);

        _M_allCities.put("NewYork", new City(name, p, routes));


        //--------------------------------Boston-------------------------//
        name = "Boston";
        p = new PointF(1981, 364);
        routes = new HashMap<>();


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("NewYork-Boston-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("NewYork-Boston-2");
        routeList.add(r);
        routes.put("NewYork", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Montreal-Boston-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Montreal-Boston-2");
        routeList.add(r);
        routes.put("Montreal", routeList);

        _M_allCities.put("Boston", new City(name, p, routes));


        //--------------------------------Montreal-------------------------//
        name = "Montreal";
        p = new PointF(1823, 230);
        routes = new HashMap<>();


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Montreal-NewYork");
        routeList.add(r);
        routes.put("NewYork", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Toronto-Montreal");
        routeList.add(r);
        routes.put("Toronto", routeList);

        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("SaultSt.Marie-Montreal");
        routeList.add(r);
        routes.put("SaultSt.Marie", routeList);


        routeList = new ArrayList<>();
        r = Route.get_RoutesMap().get("Montreal-Boston-1");
        routeList.add(r);
        r = Route.get_RoutesMap().get("Montreal-Boston-2");
        routeList.add(r);
        routes.put("Boston", routeList);

        _M_allCities.put("Montreal", new City(name, p, routes));

    }



    public static void initAdjacentCities(){

        City city;
        List<City> adjCities;

        city = _M_allCities.get("Vancouver");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Calgary"));
        adjCities.add(_M_allCities.get("Seattle"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Seattle");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Vancouver"));
        adjCities.add(_M_allCities.get("Calgary"));
        adjCities.add(_M_allCities.get("Helena"));
        adjCities.add(_M_allCities.get("Portland"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Portland");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Seattle"));
        adjCities.add(_M_allCities.get("SaltLakeCity"));
        adjCities.add(_M_allCities.get("SanFrancisco"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("SanFrancisco");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Portland"));
        adjCities.add(_M_allCities.get("SaltLakeCity"));
        adjCities.add(_M_allCities.get("LosAngeles"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("LosAngeles");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("SanFrancisco"));
        adjCities.add(_M_allCities.get("LasVegas"));
        adjCities.add(_M_allCities.get("Phoenix"));
        adjCities.add(_M_allCities.get("ElPaso"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("LasVegas");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("LosAngeles"));
        adjCities.add(_M_allCities.get("SaltLakeCity"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("SaltLakeCity");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("LasVegas"));
        adjCities.add(_M_allCities.get("SanFrancisco"));
        adjCities.add(_M_allCities.get("Portland"));
        adjCities.add(_M_allCities.get("Helena"));
        adjCities.add(_M_allCities.get("Denver"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Helena");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("SaltLakeCity"));
        adjCities.add(_M_allCities.get("Seattle"));
        adjCities.add(_M_allCities.get("Calgary"));
        adjCities.add(_M_allCities.get("Winnipeg"));
        adjCities.add(_M_allCities.get("Duluth"));
        adjCities.add(_M_allCities.get("Omaha"));
        adjCities.add(_M_allCities.get("Denver"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Calgary");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Vancouver"));
        adjCities.add(_M_allCities.get("Seattle"));
        adjCities.add(_M_allCities.get("Helena"));
        adjCities.add(_M_allCities.get("Winnipeg"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Winnipeg");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Calgary"));
        adjCities.add(_M_allCities.get("Duluth"));
        adjCities.add(_M_allCities.get("Helena"));
        adjCities.add(_M_allCities.get("SaultSt.Marie"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Duluth");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Winnipeg"));
        adjCities.add(_M_allCities.get("Helena"));
        adjCities.add(_M_allCities.get("Omaha"));
        adjCities.add(_M_allCities.get("Chicago"));
        adjCities.add(_M_allCities.get("Toronto"));
        adjCities.add(_M_allCities.get("SaultSt.Marie"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Omaha");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Duluth"));
        adjCities.add(_M_allCities.get("Helena"));
        adjCities.add(_M_allCities.get("Denver"));
        adjCities.add(_M_allCities.get("KansasCity"));
        adjCities.add(_M_allCities.get("Chicago"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("KansasCity");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Omaha"));
        adjCities.add(_M_allCities.get("Denver"));
        adjCities.add(_M_allCities.get("OklahomaCity"));
        adjCities.add(_M_allCities.get("SaintLouis"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Denver");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Omaha"));
        adjCities.add(_M_allCities.get("Helena"));
        adjCities.add(_M_allCities.get("SaltLakeCity"));
        adjCities.add(_M_allCities.get("Phoenix"));
        adjCities.add(_M_allCities.get("SantaFe"));
        adjCities.add(_M_allCities.get("OklahomaCity"));
        adjCities.add(_M_allCities.get("KansasCity"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("SantaFe");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Denver"));
        adjCities.add(_M_allCities.get("Phoenix"));
        adjCities.add(_M_allCities.get("ElPaso"));
        adjCities.add(_M_allCities.get("OklahomaCity"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Phoenix");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Denver"));
        adjCities.add(_M_allCities.get("SantaFe"));
        adjCities.add(_M_allCities.get("ElPaso"));
        adjCities.add(_M_allCities.get("LosAngeles"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("ElPaso");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("SantaFe"));
        adjCities.add(_M_allCities.get("Phoenix"));
        adjCities.add(_M_allCities.get("LosAngeles"));
        adjCities.add(_M_allCities.get("Houston"));
        adjCities.add(_M_allCities.get("Dallas"));
        adjCities.add(_M_allCities.get("OklahomaCity"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Houston");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("ElPaso"));
        adjCities.add(_M_allCities.get("Dallas"));
        adjCities.add(_M_allCities.get("NewOrleans"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Dallas");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Houston"));
        adjCities.add(_M_allCities.get("ElPaso"));
        adjCities.add(_M_allCities.get("OklahomaCity"));
        adjCities.add(_M_allCities.get("LittleRock"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("OklahomaCity");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Dallas"));
        adjCities.add(_M_allCities.get("ElPaso"));
        adjCities.add(_M_allCities.get("SantaFe"));
        adjCities.add(_M_allCities.get("Denver"));
        adjCities.add(_M_allCities.get("KansasCity"));
        adjCities.add(_M_allCities.get("LittleRock"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("LittleRock");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("OklahomaCity"));
        adjCities.add(_M_allCities.get("Dallas"));
        adjCities.add(_M_allCities.get("NewOrleans"));
        adjCities.add(_M_allCities.get("Nashville"));
        adjCities.add(_M_allCities.get("SaintLouis"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("SaintLouis");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("LittleRock"));
        adjCities.add(_M_allCities.get("KansasCity"));
        adjCities.add(_M_allCities.get("Chicago"));
        adjCities.add(_M_allCities.get("Pittsburgh"));
        adjCities.add(_M_allCities.get("Nashville"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Chicago");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("SaintLouis"));
        adjCities.add(_M_allCities.get("Omaha"));
        adjCities.add(_M_allCities.get("Duluth"));
        adjCities.add(_M_allCities.get("Toronto"));
        adjCities.add(_M_allCities.get("Pittsburgh"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Toronto");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Chicago"));
        adjCities.add(_M_allCities.get("Duluth"));
        adjCities.add(_M_allCities.get("SaultSt.Marie"));
        adjCities.add(_M_allCities.get("Montreal"));
        adjCities.add(_M_allCities.get("Pittsburgh"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("SaultSt.Marie");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Toronto"));
        adjCities.add(_M_allCities.get("Duluth"));
        adjCities.add(_M_allCities.get("Winnipeg"));
        adjCities.add(_M_allCities.get("Montreal"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Montreal");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("SaultSt.Marie"));
        adjCities.add(_M_allCities.get("Toronto"));
        adjCities.add(_M_allCities.get("NewYork"));
        adjCities.add(_M_allCities.get("Boston"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Boston");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Montreal"));
        adjCities.add(_M_allCities.get("NewYork"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("NewYork");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Boston"));
        adjCities.add(_M_allCities.get("Montreal"));
        adjCities.add(_M_allCities.get("Pittsburgh"));
        adjCities.add(_M_allCities.get("Washington"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Pittsburgh");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("NewYork"));
        adjCities.add(_M_allCities.get("Toronto"));
        adjCities.add(_M_allCities.get("Chicago"));
        adjCities.add(_M_allCities.get("SaintLouis"));
        adjCities.add(_M_allCities.get("Nashville"));
        adjCities.add(_M_allCities.get("Raleigh"));
        adjCities.add(_M_allCities.get("Washington"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Nashville");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Pittsburgh"));
        adjCities.add(_M_allCities.get("SaintLouis"));
        adjCities.add(_M_allCities.get("LittleRock"));
        adjCities.add(_M_allCities.get("Atlanta"));
        adjCities.add(_M_allCities.get("Raleigh"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Atlanta");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Nashville"));
        adjCities.add(_M_allCities.get("NewOrleans"));
        adjCities.add(_M_allCities.get("Miami"));
        adjCities.add(_M_allCities.get("Charleston"));
        adjCities.add(_M_allCities.get("Raleigh"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("NewOrleans");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Atlanta"));
        adjCities.add(_M_allCities.get("LittleRock"));
        adjCities.add(_M_allCities.get("Houston"));
        adjCities.add(_M_allCities.get("Miami"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Miami");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("NewOrleans"));
        adjCities.add(_M_allCities.get("Atlanta"));
        adjCities.add(_M_allCities.get("Charleton"));
        city.set_adjacentCities(adjCities);

        city = _M_allCities.get("Charleston");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Miami"));
        adjCities.add(_M_allCities.get("Atlanta"));
        adjCities.add(_M_allCities.get("Raleigh"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Raleigh");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Charleston"));
        adjCities.add(_M_allCities.get("Atlanta"));
        adjCities.add(_M_allCities.get("Nashville"));
        adjCities.add(_M_allCities.get("Pittsburgh"));
        adjCities.add(_M_allCities.get("Washington"));
        city.set_adjacentCities(adjCities);


        city = _M_allCities.get("Washington");
        adjCities = new ArrayList<>();
        adjCities.add(_M_allCities.get("Raleigh"));
        adjCities.add(_M_allCities.get("Pittsburgh"));
        adjCities.add(_M_allCities.get("NewYork"));
        city.set_adjacentCities(adjCities);
    }


    public static void initCityPoints(){

        List<City> cities = get_allCities();

        for(City c : cities){
            RectF rect = new RectF();
            if(c.get_coordinate() == null){
                int x = 1;
            }
            rect.set(c.get_coordinate().x - 15, c.get_coordinate().y - 15,
                    c.get_coordinate().x + 15, c.get_coordinate().y + 15 );

            c.set_cityPointArea(rect);
        }
    }

}
