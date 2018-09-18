package com.sid.evoting.controller;

import com.sid.evoting.entities.*;
import com.sid.evoting.metiers.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
public class DashboardController {
    @Autowired
    private INotification iNotification;
    @Autowired
    private IBulletin iBulletin;
    @Autowired
    private ICandidat iCandidat;
    @Autowired
    private IUser iUser;
    @Autowired
    private IElecteur iElecteur;
    @Autowired
    private IDepartement iDepartement;
    @Autowired
    private IElection iElection;
    @Autowired
    private IService iService;
    @Autowired
    private INiveau iNiveau;
    //@Autowired
    //private IFiliere iFiliere;

    @GetMapping("/menu_electeur")
    public String menu_electeur(Model model){
        Integer nombre=iNotification.nombreNotif();
        String notifi="notification";
        List<Election> elections = iElection.listElection();
        List<Departement> departements = iDepartement.listDepartement();
        //List<Filiere> filieres = iFiliere.listFiliere();
        List<Niveau> niveaus = iNiveau.listNiveaux();
        List<Service> services = iService.listService();

        List<Notification> notifications=iNotification.notifs();
        Election electionOuvert = iElection.getElectionOuvert();

        model.addAttribute("nombre",nombre);
        model.addAttribute("notifi",notifi);
        model.addAttribute("notifications",notifications);
        model.addAttribute("elections",elections);
        model.addAttribute("departements",departements);
        //model.addAttribute("filieres",filieres);
        model.addAttribute("services",services);
        model.addAttribute("niveaus",niveaus);
        model.addAttribute("electionOuvert",electionOuvert);
        return "menu_electeur";
    }

    @GetMapping("/menu_superviseur")
    public String menu_superviseur(Model model,HttpServletRequest request){
        Superviseur superviseur= (Superviseur) iUser.userLogged(request);
        Integer nombreInscrits = iCandidat.nombreCandidatInscrit();
        //nombre electeur
        Integer nbElecteurv=iElecteur.nombreElecteurVoter();
        Integer nbElecteurNV=iElecteur.nombreElecteurVoterN();
        Integer total = nbElecteurv + nbElecteurNV;
        //election ouverte au niveau de la plateforme
        Election election = iElection.getElectionOuvert();
        model.addAttribute("nombreInscrits",nombreInscrits);
        model.addAttribute("total",total);
        model.addAttribute("election",election);
        model.addAttribute("superviseur",superviseur);
        return "menu_superviseur";
    }

    @GetMapping("/avance_vote")
    public String avance(Model model, HttpServletRequest request){
        Superviseur user = (Superviseur) iUser.userLogged(request);
        String name = user.getName();
        //on recupere l'election supervisee
        Election election = iElection.getElectionSuper(user.getIdUser());
        String nomElection = election.getNomElection();
        Date dateE=new Date();
        List<Candidat> candidats=iCandidat.listCandidat();
        for (Candidat c: candidats){
            Long id=c.getIdUser();
            Integer voix = iBulletin.nombreVoixCandidat(id);
            c.setVoix(voix);
        }
        //nombre electeur
        Integer nbElecteurv=iElecteur.nombreElecteurVoter();
        Integer nbElecteurNV=iElecteur.nombreElecteurVoterN();
        Integer total = nbElecteurv + nbElecteurNV;

        //le nombre inscrit
        Integer nombreInscrit = iCandidat.nombreCandidatInscrit();

        Candidat candidatGagnant = gagnant(candidats);
        //je recupere l'election


        model.addAttribute("candidats",candidats);
        model.addAttribute("name",name);
        model.addAttribute("nombreV",nbElecteurv);
        model.addAttribute("nombreNV",nbElecteurNV);
        model.addAttribute("nombreInscrit",nombreInscrit);
        model.addAttribute("total",total);
        model.addAttribute("nomElection",nomElection);
        model.addAttribute("dateE",dateE);
        model.addAttribute("election",election);
        model.addAttribute("candidatGagnant",candidatGagnant);
        return "avance_vote";
    }

    //pour le PV de resultat
    @GetMapping("/PVResultat")
    public String pv(){
        return "PVResultat";
    }

    //pour le menu candidat
    @GetMapping("/menu_candidat")
    public String candi(Model model,HttpServletRequest request){
        //recuperer utilisateur connecte
        Candidat candidat= (Candidat) iUser.userLogged(request);
        String name = candidat.getName();
        //liste des departements
        List<Departement> departements = iDepartement.listDepartement();

        model.addAttribute("name",name);

        model.addAttribute("departements",departements);
        model.addAttribute("candidat",candidat);
        return "menu_candidat";
    }

    @GetMapping("/ResultatPrint")
    public String resultat(Model model,HttpServletRequest request){
        Superviseur user = (Superviseur) iUser.userLogged(request);
        String name = user.getName();
        //on recupere l'election supervisee
        Election election = iElection.getElectionSuper(user.getIdUser());
        String nomElection = election.getNomElection();
        Date dateE=new Date();
        List<Candidat> candidats=iCandidat.listCandidat();
        for (Candidat c: candidats){
            Long id=c.getIdUser();
            Integer voix = iBulletin.nombreVoixCandidat(id);
            c.setVoix(voix);
        }

        //avoir le gagnant
         Candidat candidatGagnant = gagnant(candidats);
        //nombre electeur
        Integer nbElecteurv=iElecteur.nombreElecteurVoter();
        Integer nbElecteurNV=iElecteur.nombreElecteurVoterN();
        Integer total = nbElecteurv + nbElecteurNV;

        //je recupere l'election


        model.addAttribute("candidats",candidats);
        model.addAttribute("name",name);
        model.addAttribute("nombreV",nbElecteurv);
        model.addAttribute("nombreNV",nbElecteurNV);
        model.addAttribute("total",total);
        model.addAttribute("nomElection",nomElection);
        model.addAttribute("dateE",dateE);
        model.addAttribute("election",election);
        model.addAttribute("candidatGagnant",candidatGagnant);
        return "ResultatPrint";
    }

    //trouver le candidat ayant gagne
    private Candidat gagnant(List<Candidat> candidats){
        int max = 0;
        Candidat  c =new Candidat();
        for(Candidat ca:candidats){
            int voix = iBulletin.nombreVoixCandidat(ca.getIdUser());
            ca.setVoix(voix);
            if(ca.getVoix()>max){
                max=ca.getVoix();
                c = ca;
            }
        }
        return c;
    }

    //test
    @GetMapping("/gagnant")
    @ResponseBody
    public Candidat gagne(){
        List<Candidat> candidats = iCandidat.listCandidat();
        Candidat c = gagnant(candidats);
        return c;
    }
}
