import type {JSX} from "react";

export type Tag = {
	name: string
	id: number
}
export default function PopularTags({tags, addTab}: {tags: Tag[], addTab: (tab: {label: string, value: string, content: JSX.Element}) => void}) {
	return (
			<>
				<p>Popular Tags</p>
				<div className="flex flex-wrap gap-1 mt-2">
					{
						tags.map((tag) => (
								<button
										key={tag.id}
										className="bg-card-foreground text-card rounded-full text-xs"
										onClick={() => addTab({label: `#${tag.name}`, value: tag.name, content:
											(
													<p>{tag.name}</p>
											)
											})}
								>
									{tag.name}
								</button>
						))
					}
				</div>
			</>
	)
}