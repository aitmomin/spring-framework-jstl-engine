package org.Mcloud.bq.controleur;

import java.util.Date;

import javax.inject.Inject;
import javax.validation.Valid;

import org.Mcloud.bq.entities.Client;
import org.Mcloud.bq.entities.Compte;
import org.Mcloud.bq.entities.CompteCourant;
import org.Mcloud.bq.entities.CompteEpargne;
import org.Mcloud.bq.entities.Employe;
import org.Mcloud.bq.entities.Groupe;
import org.Mcloud.bq.metier.BanqueRemote;
import org.Mcloud.bq.models.ClientModel;
import org.Mcloud.bq.models.CompteModel;
import org.Mcloud.bq.models.ConsulteCompteModel;
import org.Mcloud.bq.models.EmployeModel;
import org.Mcloud.bq.models.GroupeModel;
import org.Mcloud.bq.models.OperationModel;
import org.Mcloud.bq.models.RechercheClientModel;
import org.Mcloud.bq.models.RechercheCompteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BanqueServlet {
	@Inject
	BanqueRemote metier;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home() {
		// model.addAttribute("serverTime", formattedDate );
		return "index";
	}

	@RequestMapping(value = "/creerCompte")
	public String creerCompte(Model model) {// CompteModel cm, BindingResult
											// bindingResult,
		CompteModel cm = new CompteModel();
		model.addAttribute("compteModel", cm);
		return "creerCompte";
	}

	@RequestMapping(value = "/ajoutEmploye")
	public ModelAndView ajoutEmploye() {
		ModelAndView mv = new ModelAndView("ajoutEmploye");
		EmployeModel em = new EmployeModel();
		mv.addObject("employeModel", em);
		return mv;
	}

	@RequestMapping(value = "/saveEmploye")
	public String saveEmploye(@Valid EmployeModel em, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "ajoutEmploye";
		}
		Employe e = new Employe(em.getNomEmploye());
		try {
			metier.addEmploye(e, em.getIdEmploye());
		} catch (Exception ex) {
			em.setErreur(ex.getMessage());
		}
		return "ajoutEmploye";
	}

	@RequestMapping(value = "/ajoutGroupe")
	public String ajoutGroupe(GroupeModel gm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "ajoutGroupe";
		}
		if (gm.getNomGroupe() != null)
			metier.addGroupe(new Groupe(gm.getNomGroupe()));
		return "ajoutGroupe";
	}

	@RequestMapping(value = "/saveCompte")
	public String saverCompte(@Valid CompteModel cm, BindingResult bindingResult, Model model) {// CompteModel
																								// cm,
																								// BindingResult
																								// bindingResult,
		if (bindingResult.hasErrors())
			return "creerCompte";
		try {
			if (cm.getTypeCompte().equals("CC")) {
				System.out.println(cm.getDecouvert());
				metier.addCompte(new CompteCourant(new Date(), cm.getMontant(), cm.getDecouvert()), cm.getIdClient(),
						cm.getIdEmploye());
			} else {
				System.out.println(cm.getTauxInteret());
				metier.addCompte(new CompteEpargne(new Date(), cm.getMontant(), cm.getTauxInteret()), cm.getIdClient(),
						cm.getIdEmploye());
			}
		} catch (Exception e) {
			cm.setErreur(e.getMessage());
		}
		model.addAttribute("compteModel", cm);
		return "creerCompte";
	}
	@RequestMapping(value = "/ajoutClient")
	public ModelAndView ajoutClient() {
		ModelAndView mv = new ModelAndView("ajoutClient");
		ClientModel cm = new ClientModel();
		mv.addObject("clientModel", cm);
		return mv;
	}

	@RequestMapping(value = "/saveClient")
	public String saveClient(@Valid ClientModel cm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "ajoutClient";
		}
		Client c = new Client(cm.getNomClient());
		metier.addClient(c);
		return "ajoutClient";
	}
	@RequestMapping(value = "/ajoutOperation")
	public ModelAndView ajoutOperation() {
		ModelAndView mv = new ModelAndView("ajoutOperation");
		OperationModel om = new OperationModel();
		mv.addObject("operationModel", om);
		return mv;
	}

	@RequestMapping(value = "/saveOperation")
	public String saveOperation(@Valid OperationModel om, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "ajoutOperation";
		}
		if(om.getTypeOperation().equals("VER"))
		{
			Compte c=metier.findCompte(om.getIdCompte());
			metier.verser(c, om.getMontant(), om.getIdEmploye());
		}
		else if(om.getTypeOperation().equals("RET"))
		{
			Compte c=metier.findCompte(om.getIdCompte());
			metier.retrait(c, om.getMontant(), om.getIdEmploye());
		}
		else
		{
			Compte c=metier.findCompte(om.getIdCompte());
			Compte c2=metier.findCompte(om.getIdCompte2());
			metier.virement(c,c2, om.getMontant(), om.getIdEmploye());
		}
		return "ajoutOperation";
	}
	@RequestMapping(value = "/consulteCompte")
	public String consulteCompte(Model model) {
		ConsulteCompteModel ccm=new ConsulteCompteModel();
		model.addAttribute("consulteCompteModel", ccm);
		return "consulteCompte";
	}
	@RequestMapping(value = "/chargerCompte")
	public String chargerCompte(@Valid ConsulteCompteModel ccm,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
		{
			return "consulteCompte";
		}
		try
		{
			Compte c=metier.findCompte(ccm.getIdCompte());
			ccm.setC(c);
			ccm.setTypeCompte(c.getClass().getSimpleName());
			long nbops=metier.getNombreOperations(ccm.getIdCompte());
			System.out.println(nbops);
			int nr=ccm.getNbEnregistrements();
			ccm.setNbPages((int) Math.ceil(((double)nbops)/ccm.getNbEnregistrements()));
			System.out.println(ccm.getNbPages());
			ccm.setOps(metier.listOperations(ccm.getIdCompte(),ccm.getPage()*nr,nr));
			model.addAttribute("consulteCompteModel", ccm);
		}
		catch(Exception ex)
		{
			ccm.setErreur(ex.getMessage());
		}
		return "consulteCompte";
	}
	@RequestMapping(value = "/rechercheClient")
	public String rechercheClient(Model model) {
		RechercheClientModel rcm=new RechercheClientModel();
		model.addAttribute("rechercheClientModel", rcm);
		return "rechercheClients";
	}
	@RequestMapping(value = "/chargerClients")
	public String rechercheClient(@Valid RechercheClientModel rcm,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
		{
			return "rechercheClients";
		}
		rcm.setClients(metier.listClients(rcm.getNomClient()));
		return "rechercheClients";
	}
@RequestMapping(value = "/compteClient")
public String compteClient(Model model) {
	RechercheCompteModel rcm=new RechercheCompteModel();
	model.addAttribute("rechercheCompteModel", rcm);
	return "compteClient";
}
@RequestMapping(value = "/chargerComptes")
public String compteClient(@Valid RechercheCompteModel rcm, BindingResult bindingResult, Model model) {
	if(bindingResult.hasErrors())
	{
		return "compteClient";
	}
	rcm.setComptes(metier.listCompteByClient(rcm.getIdClient()));
	return "compteClient";
}
}
