package br.edu.ifpb.initapplication;

import br.edu.ifpb.util.RestoreByCSV;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class InitRestores {

    @Inject
    private RestoreByCSV restoreByCSV;

    @PostConstruct
    public void initRestores() {
        restoreByCSV.restoreIdiomasCSV();
        restoreByCSV.restoreHabilidadesCSV();
        restoreByCSV.restoreAtitudesCSV();
    }

}
