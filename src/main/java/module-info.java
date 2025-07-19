module br.edu.ufersa.poo.pizzaria {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jakarta.persistence;
    requires java.desktop;
    requires org.hibernate.orm.core;

    opens br.edu.ufersa.poo.pizzaria.model.entities;

    opens br.edu.ufersa.poo.pizzaria.view to javafx.fxml;
    exports br.edu.ufersa.poo.pizzaria.view;

    opens br.edu.ufersa.poo.pizzaria.controller to javafx.fxml;
    exports br.edu.ufersa.poo.pizzaria.controller;
    opens br.edu.ufersa.poo.pizzaria.exceptions to javafx.fxml;
    exports br.edu.ufersa.poo.pizzaria.exceptions;


}