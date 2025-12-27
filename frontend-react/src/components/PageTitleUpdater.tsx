import { useMatches } from 'react-router-dom';
import { useEffect } from 'react';

interface RouteHandle {
	title?: string;
}

export default function PageTitleUpdater() {
	const matches = useMatches();

	useEffect(() => {
		const latestMatch = matches[matches.length - 1];
		const handle = latestMatch?.handle as RouteHandle;
		const title = handle?.title;

		if (title) {
			document.title = `${title} - Conduit`;
		} else {
			document.title = 'Conduit';
		}
	}, [matches]);

	return null;
}
