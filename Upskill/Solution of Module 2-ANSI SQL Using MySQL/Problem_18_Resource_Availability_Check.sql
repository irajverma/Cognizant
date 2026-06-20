-- 18. Resource Availability Check
-- List all events that do not have any resources uploaded.

SELECT event_id, title
FROM Events
WHERE event_id NOT IN (SELECT DISTINCT event_id FROM Resources);