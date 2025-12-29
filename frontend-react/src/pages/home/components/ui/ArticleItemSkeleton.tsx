import {Skeleton} from "@/components/ui/skeleton.tsx";

export default function ArticleItemSkeleton() {

	return (
		<div className="space-y-4">
			<div className="flex items-center space-x-4">
				<Skeleton className="h-12 w-12 rounded-full" />
				<div className="space-y-2">
					<Skeleton className="h-4 w-[50px]" />
					<Skeleton className="h-4 w-[70px]" />
				</div>
				<Skeleton className="h-8 w-[100px] ml-auto" />
			</div>
			<div className="space-y-2">
				<Skeleton className="h-4 w-[250px]" />
				<Skeleton className="h-4 w-[500px]" />
			</div>
			<div className="flex">
				<Skeleton className="h-4 w-[100px]" />
				<div className="space-x-2 ml-auto flex">
					<Skeleton className="h-4 w-[50px]" />
					<Skeleton className="h-4 w-[50px]" />
					<Skeleton className="h-4 w-[50px]" />
				</div>
			</div>
		</div>
	)
}