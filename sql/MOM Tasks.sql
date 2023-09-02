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
    s.id NOT IN (1 , 4) AND p.ID = 10
        AND m.id = 46
ORDER BY p.name