/* Liste des projets  S2M*/



/* Liste des projets  S2M  president VIEW*/

	SELECT 
    p.id, upper(p.name) 'Projet' , 
    upper(pc.category) 'Category',  
    pr.code 'Priorité', 
    concat(p.progress, "%") 'Progress', 
    ps.status, 
    concat('Q',QUARTER(p.start_date),'/',YEAR(p.start_date)) 'Début',
    concat('Q',QUARTER(p.initial_end_date),'/',YEAR(p.initial_end_date)) 'Fin Prévue',  	 
    p.real_endt_date 'Fin réelle',
    p.todo
FROM
    `my-pro-apps-prod`.project p
        LEFT OUTER JOIN
    project_priority pr ON p.project_priority_id = pr.id
        LEFT OUTER JOIN
    project_status_code ps ON ps.id = p.project_status_code_id
    LEFT OUTER JOIN project_category pc on p.project_category_id = pc.id
    WHERE p.company_id=1
ORDER BY pr.ID