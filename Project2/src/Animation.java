public class Animation implements Action
{

    private Entity entity;
    private WorldModel world;
    private ImageStore imageStore;
    private int repeatCount;

    public Animation(Entity entity, WorldModel world, ImageStore imageStore, int repeatCount)
    {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }

    public Entity getEntity()
    {
        return this.entity;
    }

    public void setEntity(Entity e)
    {
        this.entity = e;
    }

    public WorldModel getWorld()
    {
        return this.world;
    }

    public void setWorld(WorldModel w)
    {
        this.world = w;
    }

    public ImageStore getImageStore()
    {
        return this.imageStore;
    }

    public void setImageStore(ImageStore i)
    {
        this.imageStore = i;
    }

    public int getRepeatCount()
    {
        return this.repeatCount;
    }

    public void setRepeatCount(int r)
    {
        this.repeatCount = r;
    }


    public void executeAction(EventScheduler scheduler)
    {
        executeAnimationAction(scheduler);
    }

    private void executeAnimationAction(EventScheduler scheduler)
    {
       this.entity.nextImage();

       if (this.repeatCount != 1)
       {
           if (this.entity instanceof MinerFull)
           {
               scheduler.scheduleEvent(this.entity,
                       createAnimationAction(Math.max(this.repeatCount - 1, 0)),
                       ((MinerFull)this.entity).getAnimationPeriod());
           }

           else if (this.entity instanceof MinerNotFull)
           {
               scheduler.scheduleEvent(this.entity,
                       createAnimationAction(Math.max(this.repeatCount - 1, 0)),
                       ((MinerNotFull)this.entity).getAnimationPeriod());
           }

           else if (this.entity instanceof Ore)
           {
               scheduler.scheduleEvent(this.entity,
                       createAnimationAction(Math.max(this.repeatCount - 1, 0)),
                       ((Ore)this.entity).getAnimationPeriod());
           }

           else if (this.entity instanceof OreBlob)
           {
               scheduler.scheduleEvent(this.entity,
                       createAnimationAction(Math.max(this.repeatCount - 1, 0)),
                       ((OreBlob)this.entity).getAnimationPeriod());
           }

           else if (this.entity instanceof Quake)
           {
               scheduler.scheduleEvent(this.entity,
                       createAnimationAction(Math.max(this.repeatCount - 1, 0)),
                       ((Quake)this.entity).getAnimationPeriod());
           }

           else if (this.entity instanceof Vein)
           {
               scheduler.scheduleEvent(this.entity,
                       createAnimationAction(Math.max(this.repeatCount - 1, 0)),
                       ((Vein)this.entity).getAnimationPeriod());
           }

           else
           {
               throw new UnsupportedOperationException(
                       String.format("executeActivityAction not supported for %s", "this.entity.kind"));
           }
       }
    }

    public Action createAnimationAction(int repeatCount)
    {
        return new Animation(this.entity, null, null, repeatCount);
    }


}