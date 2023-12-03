package com.example.springaventure.controller.admin

import com.example.springaventure.model.dao.PotionDao
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * Contrôleur responsable de la gestion des potions dans la partie administrative de l'application.
 */
@Controller
class PotionControleur(
    /** DAO pour l'accès aux données des potions. */
    val potionDao: PotionDao
) {

    /**
     * Affiche la liste des potions dans la page d'index.
     *
     * @param model Modèle utilisé pour transmettre des données à la vue.
     * @return Le nom de la vue à afficher.
     */
    @GetMapping("/admin/potion")
    fun index(model: Model): String {
        // Récupère toutes les potions depuis la base de données
        val potions = this.potionDao.findAll()
        // Ajoute la liste des potions au modèle pour transmission à la vue
        model.addAttribute("potions", potions)
        // Retourne le nom de la vue à afficher
        return "admin/potion/index"
    }


    @GetMapping("/admin/potion/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la potion avec l'ID spécifié depuis la base de données
        val unePotion = this.potionDao.findById(id).orElseThrow()

        // Ajoute la potion au modèle pour affichage dans la vue
        model.addAttribute("potion", unePotion)

        // Retourne le nom de la vue à afficher
        return "admin/potion/show"
    }
}
