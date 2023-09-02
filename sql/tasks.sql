Laste modified 27/07/23

/*
      Liste des actions prevue pour ajourh'hui 
*/
SELECT 
    t.id 'TASK ID',
    p.code 'projet',
    t.subject,
    t.task_title,
    t.deal_line,
    t.is_urgent,
    s.code 'Status'
FROM
    `my-pro-apps-prod`.task t,
    `my-pro-apps-prod`.project p,
    `my-pro-apps-prod`.task_status s
WHERE
    t.task_status_id <> 1
        AND p.id = t.project_name_id
        AND s.id = t.task_status_id
        AND t.deal_line = curdate()


/*
      Liste des actions en retard
*/
SELECT 
   
    t.id 'TASK ID',
     p.code 'projet',
    t.subject,
    t.task_title,
    t.deal_line,
    t.is_urgent,
    s.code 'Status'
FROM
    `my-pro-apps-prod`.task t,
    `my-pro-apps-prod`.project p,
    `my-pro-apps-prod`.task_status s
WHERE
    t.task_status_id <> 1
        AND p.id = t.project_name_id
        AND s.id = t.task_status_id
        AND t.deal_line < curdate()

https://www.vwpassat.eu/ucp.php?mode=register
https://www.vwpassat.eu/index.php
marouane
mlouah@gmail.com
vw2023@mMM


/*
      Liste des actions ouvertes
*/
SELECT 
    IFNULL(UPPER(p.code), '- ') 'Projet',
    t.id 'Task ID',
    t.task_title 'Task',
    DATE_FORMAT(t.date_creation, '%Y-%m-%d') 'Task Crea',
    IFNULL(CONCAT(m.title, '[', m.id, '] ', m.meeting_date),
            '-') 'MoM [ID]/Date',
    s.code 'Status',
    t.deal_line 'DL',
    IF(CURDATE() - t.deal_line > 0,
        CURDATE() - t.deal_line,
        '') 'Delay'
FROM
    task t
        LEFT OUTER JOIN
    project p ON t.project_name_id = p.id
        LEFT OUTER JOIN
    task_status s ON t.task_status_id = s.id
        LEFT OUTER JOIN
    mo_m m ON t.task_mom_id = m.id
WHERE
    s.id NOT IN (1 , 4)
ORDER BY p.name

/*
      Liste des actions [OUVERTES] pour un projet 
*/

SELECT 
    IFNULL(UPPER(p.code), '- ') 'Projet',
    t.id 'Task ID',
    t.task_title 'Task',
    DATE_FORMAT(t.date_creation, '%Y-%m-%d') 'Task Crea',
    IFNULL(CONCAT(m.title, '[', m.id, '] ', m.meeting_date),
            '-') 'MoM [ID]/Date',
    s.code 'Status',
    t.deal_line 'DL',
    IF(CURDATE() - t.deal_line > 0,
        CURDATE() - t.deal_line,
        '') 'Delay'
FROM
    task t
        LEFT OUTER JOIN
    project p ON t.project_name_id = p.id
        LEFT OUTER JOIN
    task_status s ON t.task_status_id = s.id
        LEFT OUTER JOIN
    mo_m m ON t.task_mom_id = m.id
WHERE
    s.id NOT IN (1 , 4)
    and p.ID=10
ORDER BY p.name

/*
      Action(s) d'un MoM
*/

SELECT 
    t.id,
    t.subject,
    t.task_title,
    t.deal_line,
    t.is_urgent,
    s.code,
    t.notes
FROM
    `my-pro-apps-prod`.task t,
    `my-pro-apps-prod`.mo_m m,
    `my-pro-apps-prod`.task_status s
WHERE
    t.task_mom_id = 25
        AND m.id = t.task_mom_id
        AND t.task_status_id = s.id

/*
      Liste des taches période - NON STABLE
*/

SELECT 
    p.code 'projet',
    t.id,
    t.subject,
    t.task_title,
    t.deal_line,
    t.is_urgent,
    s.code 'Status',
    t.date_modify
FROM
    `my-pro-apps-prod`.task t,
    `my-pro-apps-prod`.project p,
    `my-pro-apps-prod`.task_status s
WHERE
    t.date_modify < '2023-07-30'
        AND t.date_modify > '2023-07-13'
        AND p.id = t.project_name_id
        AND s.id = t.task_status_id
       

