-- 12. Event with Maximum Sessions
-- List the event(s) with the highest number of sessions.

SELECT event_id, COUNT(session_id) AS session_count
FROM Sessions
GROUP BY event_id
HAVING count_sessions = (
    SELECT MAX(sess_count) 
    FROM (SELECT COUNT(session_id) AS sess_count FROM Sessions GROUP BY event_id) AS temp
);

-- Alternative simpler query (using LIMIT 1 for basic database constraints):
-- SELECT event_id, COUNT(session_id) AS session_count FROM Sessions GROUP BY event_id ORDER BY session_count DESC LIMIT 1;