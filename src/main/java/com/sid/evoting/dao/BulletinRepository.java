package com.sid.evoting.dao;

import com.sid.evoting.entities.Bulletin;
import com.sid.evoting.entities.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BulletinRepository extends JpaRepository<Bulletin,Long> {
    //compter le nombre de voix
    @Query(value = "select count(b) as nombrevoix,c.name as nom from Bulletin b,Electeur e,Candidat c where b.idBulletin=e.bulletin.idBulletin and b.candidat.idUser=c.idUser and e.vote=1 group by c.name")
    public List<Bulletin> compterVoixForCandidat();

    //les candidats ayant un bulletin
    @Query("select c from Candidat c,Bulletin b where c.idUser=b.candidat.idUser")
    public List<Candidat> listCandidatBulletin();

    //candidats bulletin pour un dep donnee
    @Query("select c from Candidat c,Bulletin b ,Departement d where c.idUser=b.candidat.idUser and c.departement.idDep=d.idDep and d.idDep=:id")
    public List<Candidat> listCandidatsBulletinDep(@Param("id") Long id);

    //candidats bulletin selon le departement,filiere et niveau
    @Query("select c from Candidat c ,Bulletin b,Departement d,Filiere f,Niveau n where c.idUser=b.candidat.idUser and c.departement.idDep=d.idDep and f.departement.idDep=d.idDep and n.filiere.idfiliere=f.idfiliere and c.filiere.idfiliere=f.idfiliere and c.niveau.idNiv=n.idNiv and f.idfiliere=:idF and n.idNiv=:idn")
    public List<Candidat> listCandidatDepFN(@Param("idF") Long idF, @Param("idn") Long idn);

    //retrouver un bulletin selon un candidat
    @Query("select b from Bulletin b,Candidat c where b.candidat.idUser=c.idUser and b.candidat.idUser=:idc")
    public Bulletin bulletinC(@Param("idc") Long idc);

    //nombre de voix par candidat
    @Query("select count(b.idBulletin) from Bulletin b,Candidat c,Electeur e where b.candidat.idUser=c.idUser and e.bulletin.idBulletin =b.idBulletin and c.idUser =:id")
    public Integer compteVoixCandidat(@Param("id") Long id);

}
