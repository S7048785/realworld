
import { ArrowRight } from 'lucide-react';

export default function ButtonCreativeRight() {
	return (
			<>
				<div
						className='group relative cursor-pointer px-2 py-1 w-32 border rounded-full overflow-hidden text-gray-500 text-center text-xs'>
        <span
						className='translate-x-1 group-hover:translate-x-12 group-hover:opacity-0 transition-all duration-300 inline-block'>
          <span className=" pl-2">
						Read More
					</span>
        </span>
					<div
							className='flex gap-2 text-white z-10 items-center absolute top-0 h-full w-full justify-center translate-x-12 opacity-0 group-hover:-translate-x-1 group-hover:opacity-100 transition-all duration-300 '>
						<span>Read More</span>
						<ArrowRight size={16}/>
					</div>
					<div
							className='absolute top-[35%] left-[10%] h-2 w-2 group-hover:h-full group-hover:w-full rounded-lg bg-primary scale-[1]  group-hover:bg-primary group-hover:scale-[1.8] transition-all duration-300 group-hover:top-[0%] group-hover:left-[0%]'></div>
				</div>
			</>
	)
}