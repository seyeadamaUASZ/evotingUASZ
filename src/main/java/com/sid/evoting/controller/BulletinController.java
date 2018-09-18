package com.sid.evoting.controller;

import com.sid.evoting.entities.Bulletin;
import com.sid.evoting.entities.Candidat;
import com.sid.evoting.entities.Electeur;
import com.sid.evoting.entities.User;
import com.sid.evoting.metiers.interfaces.IBulletin;
import com.sid.evoting.metiers.interfaces.IElecteur;
import com.sid.evoting.metiers.interfaces.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BulletinController {
    @Autowired
    private IUser iUser;
    @Autowired
    private IBulletin bulletinM;
    @Autowired
    private IElecteur iElecteur;
    //le nombre de voix
    @GetMapping("/compterForCandidat")
    @ResponseBody
    public List<Bulletin> compteForCandidat(Model model){
        return bulletinM.compterVoixForCandidat();
    }

    @GetMapping("/candidatBulletin")
    @ResponseBody
    public List<Candidat> listCandidatB(Model model){
        return bulletinM.listCandidatsBulltin();
    }
    @GetMapping("/candidatBDep/{id}")
    @ResponseBody
    public List<Candidat> listCandidatBullDep(Model model, @PathVariable("id") Long id){
        return bulletinM.listCandidatsBulletinDep(id);
    }
    @GetMapping("/candidatsDepF/{idF}/{idn}")
    @ResponseBody
    public List<Candidat> lisBulletinDepF(Model model, @PathVariable("idF") Long idF, @PathVariable("idn") Long idn){
        return bulletinM.listCandidatBuDepF(idF,idn);
    }
    @GetMapping("/candidatBUser")
    public String listCandidatBulletin(Model model, HttpServletRequest request){
        User user=iUser.userLogged(request);
        if(user.getProfession().equals("enseignant")){
            Long idDep=user.getDepartement().getIdDep();
            List<Candidat> candi= bulletinM.listCandidatsBulletinDep(idDep);
            model.addAttribute("candidats",candi);
            return "BulletinCandidats";
        }
        if(user.getProfession().equals("etudiant")){
            Long idF=user.getFiliere().getIdfiliere();
            Long idn=user.getNiveau().getIdNiv();
            List<Candidat> candi= bulletinM.listCandidatBuDepF(idF,idn);
            model.addAttribute("candidats",candi);
            return "BulletinCandidats";
        }
        return null;
    }

    //le bulletin pour un candidat donner
    @GetMapping("/getBulletin/{idc}")
    @ResponseBody
    public Bulletin getBulletin(@PathVariable("idc") Long idc){
        return bulletinM.bulletinC(idc);
    }
    //la méthode de vote
    @GetMapping("/voter")
    public String voter(Model model, Long id, HttpServletRequest request){
        Electeur user= (Electeur) iUser.userLogged(request);
        Bulletin b=bulletinM.bulletinC(id);
        user.setBulletin(b);
        user.setName(user.getName());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setVote(1);
        user.setActive(false);
        iElecteur.modifierElecteur(user.getIdUser(),user);
        return "redirect:/confir";
    }
    @GetMapping("/confir")
    public String confir(Model model){
       return "confir";
    }

    //nombre de voix pour un candidat

    @GetMapping("/voixCandidat/{id}")
    @ResponseBody
    public Integer nombreVoix(Model model, @PathVariable("id") Long id){
        return bulletinM.nombreVoixCandidat(id);
    }

}
