# E5B-NSOMI

## Circonstances de l'expression des besoins
Adaptation et évolution de l'application.

## Spécifications fonctionnelles de la production attendue

### A/ Dans le tableau d'administration des "types d'armures"
Faire apparaître le nombre total de "types d'armures".

### B/ Lors de la création d'un personnage (endpoint du formulaire : /joueur/personnage/create)
Permettre au joueur de choisir une armure parmi la liste complète de toutes les armures.

### C/ Afficher sur une nouvelle page le détail d'une potion.

## Réalisation des fonctionnalités dans l'ordre suivant : B, C, A

Voici ce que j'ai fait pour la B:



```kotlin
    class PersonnageControleur(
    DAO pour l'accès aux données des personnages.
    val personnageDao: PersonnageDao,
    /** DAO pour l'accès aux données des utilisateurs. */
    val utilisateurDao: UtilisateurDao,
    val armureDao: ArmureDao
) {
     @GetMapping("/joueur/personnage/create")
    fun create(model: Model): String {
        val nouvellePersonnage = Personnage(null, "", 1, 1, 1, 1,null,null)
        val armures = armureDao.findAll()
        model.addAttribute("nouvellePersonnage", nouvellePersonnage)
        model.addAttribute("armures", armures)
        return "joueur/personnage/create"
    }
```

```html
<li class="list-group-item">
Armure :
    <div th:if="${personnage.armureEquipee!=null}"
    th:style="'color :'+${personnage.armureEquipee.qualite.getCouleur()}"
    th:text="${personnage.armureEquipee.nom}"></div>
        <div th:unless="${personnage.armureEquipee!=null}">Pas d'armure</div>
    </li>
```

Voici ce que j'ai fait pour la C:
```html
<!DOCTYPE html>
<html lang="fr" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div class="vh-100">
        <nav></nav>
        <main class="container">
            <h1 th:text="'Potion ' + ${potion.nom}"></h1>
            <ul>
                <li th:text="'Nom : ' + ${potion.nom}"></li>
                <li th:text="'Description : ' + ${potion.description}"></li>
                <li th:text="'Soin : ' + ${potion.soin}"></li>
            </ul>
        </main>
    </div>
    <footer></footer>
</body>
</html>
```
Voici la modification a faire dans le controller

```kotlin
@GetMapping("/admin/potion/{id}")
    fun show(@PathVariable id: Long, model: Model): String {
        // Récupère la potion avec l'ID spécifié depuis la base de données
        val unePotion = this.potionDao.findById(id).orElseThrow()

        // Ajoute la potion au modèle pour affichage dans la vue
        model.addAttribute("potion", unePotion)

        // Retourne le nom de la vue à afficher
        return "admin/potion/show"
    }
```